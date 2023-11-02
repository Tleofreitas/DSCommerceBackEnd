# Projeto Spring Boot Estruturado
## Requisitos do sistema
[Ver os requisitos](https://drive.google.com/file/d/1ivKMjxW6ahfjkC7AFFm8VukYBLvfIkM0/view)

---
## *Pré-requisitos para testes locais* 
Para testar localmente você deve ter instalados em sua máquina:

### 1) Descompactador de arquivos
O Windows 10 já possui um programa padrão para visualizar arquivos compactados. Caso seu sistema operacional seja anterior ao Windos 10, realize o passo abaixo:

Para visualizar o projeto você precisará extrair os arquivos através de um programa para arquivos compactados.

Recomendo Winrar, baixe a versão 32x ou 64x, de acordo com seu sistema.

Link para download: https://www.win-rar.com/download.html

### 2) IDE (Ambiente de Desenvolvimento Integrado)
Este será utilizado para executar a aplicação. Recomendo [STS](https://spring.io/tools) (Spring Tool Suit) ou [IntelliJ Community](https://www.jetbrains.com/idea/download/?section=windows)

### 3) Java JDK...
Caso você não tenha instalado e configurado em sua máquina, instale da seguinte maneira: [Instalar Java JDK 17](https://www.youtube.com/watch?v=QekeJBShCy4)

---
## 📦️ *Realizando teste localmente*
### 1) Clone (baixe) o repositório

Nesta página, clique no botão Code, depois em Download ZIP e salve o arquivo.

![image](https://github.com/Tleofreitas/DSCommerceBackEnd/assets/88738577/386584fa-f7f6-4f41-a809-af26f034654c)

Extrair arquivos: Abra a pasta onde o arquivo foi salvo.

### 2) Extração
### 2.1) Extrair arquivos sem Winrar
Clique com o botão direito sobre o arquivo e selecione Extrair Tudo.
<br></br>

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/03075095-3752-4ce1-83aa-dfda9e738466)

As informações foram extraídas para a pasta DSCommerceBackEnd-main.

### 2.2) Extrair arquivos com Winrar
Clique com o botão direito sobre o arquivo e selecione Extrair Aqui (Extract Here).
<br></br>

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/6331c260-b1a5-4fc6-84e8-a383a8dadcf3)

As informações foram extraídas para a pasta DSCommerceBackEnd-main.

### 3) Abrir Projeto
### 3.1) Abrir projeto com STS
Clique em File, Import, Maven, Existing Maven Projects, Next...
<br></br>

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/31ff2aae-32fb-44e2-944b-421516888f78)

Selecione a pasta onde você salvou o projeto, Finish
<br></br>
![image](https://github.com/Tleofreitas/DSCommerceBackEnd/assets/88738577/cc26f87f-0baa-4fc8-97b0-a41ac7b50cee)

Aguarde a importação do projeto (acompanhe a barra de carregamento no canto inferior direito).

### 3.2) Abrir projeto com IntelliJ
Clique em Open, selecione a pasta onde você salvou o projeto, dentro da pasta do projeto selecione BackEnd, Ok...
<br></br>

![image](https://github.com/Tleofreitas/DSCommerceBackEnd/assets/88738577/c26f16b5-4d1d-4866-92b6-dd9063b8ea31)

Caso apareça a tela abaixo, selecione Maven Project...

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/78d9394f-3a72-48db-bd6b-887931ee1537)

Aguarde a importação do projeto (acompanhe a barra de carregamento no canto inferior direito).

### 4) Executar o Projeto
### 4.1) Executar projeto com STS
No menu Boot Dashboard, clique com botão direito em dscommerce, (Re)start e aguarde o programa ser iniciado...

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/32f2e54f-d599-421f-a3d7-996015c5f1e5)

Neste mesmo menu, a indicação de em execução é uma seta verde para cima

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/61604bf0-1b70-4eef-a191-586cc54e17a6)

No menu Console, pode-se ver o tempo de inicialização e a indicação de processo rodando...

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/0cd3e4bf-6d7e-4704-8da3-3d94793d5cdd)

### 4.2) Executar projeto com IntelliJ
Acesse BackEnd > src > main > java > DscommerceApplication, clique com botão direito e clique em Run e aguarde o programa ser iniciado...

![image](https://github.com/Tleofreitas/DSCommerceBackEnd/assets/88738577/9fb0741e-98e5-4545-a14c-8a36456d4964)

No menu Run, pode-se ver o tempo de inicialização e a indicação de processo rodando...

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/e42bb25a-a81d-4c80-8cb1-98c625535218)

### 5) Com o Programa em Execução
Acesse o H2 DataBase com o link http://localhost:8080/h2-console

Informações de acesso:
- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Senha:

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/4ee2657b-ada6-46a9-b782-889ac83f75f1)

### Criação de tabelas
As tabelas que foram criadas podem ser vistas no lado esquerdo conforme diagrama de clases

![image](https://github.com/Tleofreitas/DSCommerceBackEnd/assets/88738577/3d9cd872-4cb2-4b74-85f0-ea160c32d4be)

### Seed de dados
Para ver o seed de dados conforme diagrama de objetos, selecione a tabela e clique em Run. Para realizar outra pesquisa, limpe a consulta anterior com Clear. O retorno da consulta aparece logo abaixo.

![image](https://github.com/Tleofreitas/DSCommerceBackEnd/assets/88738577/7b0d7efb-d469-49ed-86da-317847b8017c)

---
## ⚠️ *Erros comuns* ⚠️
### No passo Como Testar o Código>
Se não houver a opção *Extrair Tudo*, significa que não há programa instalado para manipulação de arquivos compactados.
Neste cado, seguir com o passo *Pré-requisitos para testes locais*.

### Abrir projeto com IntelliJ Community
Após o término da importação, pode ocorrer do IntelliJ não localizar o JDK, neste caso, vá em File, Settings, pesquise por JDK, selecione Importing e em JDK for importer selecione seu JDK. Aplique (Apply) e Ok.

![image](https://github.com/Tleofreitas/Mod8_SistemaDeEvento/assets/88738577/93b85b1c-8515-470f-bc40-b7ae460fbb24)

Caso você não tenha o JDK instalado, siga com o passo <i><b>Pré-requisitos para testes locais - Java JDK</b></i>.

### Abrir o projeto
Após os arquivos serem carregados, pode acontecer de aparecer um X ou um ! vermelho. Caso isso ocorra, tente as soluções deste
tutorial: https://www.youtube.com/watch?v=Je4JWWJcyo0
