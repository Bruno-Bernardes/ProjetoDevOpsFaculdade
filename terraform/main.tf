terraform {
  required_version = ">= 1.0"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }

}

provider "aws" {
  region = var.aws_region
}

# Busca a AMI mais recente do Amazon Linux 2023 em us-east-1
data "aws_ami" "amazon_linux_2023" {
  most_recent = true
  owners      = ["amazon"]

  filter {
    name   = "name"
    values = ["al2023-ami-2023*-x86_64"]
  }
}

# VPC Padrão e Subnet para simplificar a rede
resource "aws_default_vpc" "default" {}

# Security Group (Abre porta 8080 para a app Java e 22 para SSH)
resource "aws_security_group" "app_sg" {
  name        = "${var.app_name}-sg"
  description = "Permite acesso HTTP na porta ${var.app_port} e SSH"
  vpc_id      = aws_default_vpc.default.id

  ingress {
    description = "Acesso HTTP para a app Java"
    from_port   = var.app_port
    to_port     = var.app_port
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "SSH para acesso remoto"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Instância EC2 que executará o arquivo .jar
resource "aws_instance" "web" {
  ami             = data.aws_ami.amazon_linux_2023.id
  instance_type   = var.instance_type
  security_groups = [aws_security_group.app_sg.name]

  # Script de inicialização da máquina (UserData)
  user_data = <<-EOF
              #!/bin/bash
              # Atualiza o sistema e instala o OpenJDK 21
              dnf update -y
              dnf install -y java-21-amazon-corretto-devel

              # Cria diretório da aplicação e ajusta permissões
              mkdir -p /opt/app
              chmod 755 /opt/app

              # Configura a aplicação como serviço do Systemd
              cat <<'SERVICE' > /etc/systemd/system/java-app.service
              [Unit]
              Description=Java Application
              After=network.target

              [Service]
              User=ec2-user
              WorkingDirectory=/opt/app
              ExecStart=/usr/bin/java -jar /opt/app/app.jar
              SuccessExitStatus=143
              Restart=always
              RestartSec=10

              [Install]
              WantedBy=multi-user.target
              SERVICE

              systemctl daemon-reload
              systemctl enable java-app.service
              EOF

  tags = {
    Name = var.app_name
  }
}