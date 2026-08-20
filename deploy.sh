#!/bin/bash

echo "=========================================="
echo "      Iniciando Processo de Deploy        "
echo "=========================================="

# 1. Atualizar o código-fonte no servidor
echo "[1/4] Baixando atualizações do repositório..."
git pull origin main

# 2. Reconstruir a imagem Docker com o código atualizado
echo "[2/4] Gerando a nova imagem Docker..."
docker compose build

# 3. Subir/Reiniciar os containers em segundo plano (detached)
echo "[3/4] Atualizando o container em execução..."
docker compose up -d --remove-orphans

# 4. Limpeza de recursos não utilizados (imagens órfãs)
echo "[4/4] Limpando imagens antigas sem uso..."
docker image prune -f

echo "=========================================="
echo "    Deploy finalizado com sucesso!       "
echo "=========================================="