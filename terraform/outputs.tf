output "public_ip" {
  description = "IP Público da instância EC2"
  value       = aws_instance.web.public_ip
}

output "app_url" {
  description = "URL para acessar a aplicação"
  value       = "http://${aws_instance.web.public_ip}:${var.app_port}"
}