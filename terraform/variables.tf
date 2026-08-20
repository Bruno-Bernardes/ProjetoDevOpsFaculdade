variable "aws_region" {
  type        = string
  default     = "us-east-1"
  description = "Região da AWS"
}

variable "app_name" {
  type        = string
  default     = "java-app"
  description = "Nome do projeto"
}

variable "instance_type" {
  type        = string
  default     = "t2.micro" # Elegível para Free Tier da AWS
  description = "Tipo da instância EC2"
}

variable "app_port" {
  type        = number
  default     = 8080
  description = "Porta em que a aplicação Java escuta"
}