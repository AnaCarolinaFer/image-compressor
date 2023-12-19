<h1 align="center"> Compressor de imagens </h1> 

# Índice 

* [Descrição do Projeto](#descrição-do-projeto)
* [Status do Projeto](#status-do-Projeto)
* [Funcionalidades e Demonstração da Aplicação](#funcionalidades-e-demonstração-da-aplicação)
* [Acesso ao Projeto](#acesso-ao-projeto)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Pessoas Desenvolvedoras do Projeto](#pessoas-desenvolvedoras)

# Descrição do projeto 

Projeto desenvolvido como trabalho final da disciplina de Programação Concorrente da UFRJ. O trabalho se tratava de escolher um problema para o qual fosse possível projetar um algoritmo concorrente para resolvê-lo.
O projeto escolhido foi a implementação de um programa que realiza a compressão de múltiplas imagens de maneira concorrente.
Com o objetivo de verificar e analisar as vantagens da concorrência dentro do programa, foram criados 3 algoritmos:

    - realiza a compressão das imagens de maneira sequencial, com o intuito de compará-lo com os algoritmos concorrentes;
    
    - realiza a compressão das imagens de maneira concorrente, definindo blocos de imagens (escolhidas de maneira alternada) para cada thread comprimir;
    
    - realiza a compressão das imagens de maneira concorrente, pegando a próxima imagem a ser comprimida de maneira dinâmica;

# Status do projeto
> :construction: Projeto finalizado :construction:

# :hammer: Funcionalidades do projeto

- `Funcionalidade 1`: Reduzir tamanho das imagens

# 📁 Acesso ao projeto

Você pode acessar o código fonte do projeto ou baixá-lo.

# 🛠️ Abrir e rodar o projeto

Após baixar o projeto, você pode abrir o terminal e acessar o a pasta raíz do projeto. Após isso, é necessário compilar os arquivos dos algoritmos, utilizando o comando:
    `javac <nomeDoPrograma.java>`

Por fim, temos dois formatos de comando a serem digitados, dependendo de qual programa será executado. Teremos:

    - para o programa sequencial - compresSeq - use: 
        `java <nomeDoPrograma> <nomeDaPastaDeEntrada> <nomeDaPastaDeSaida> <numeroDeImagens>`

    - para os programas concorrentes - compressDinamico ou compressBlocks - use: 
        `java <nomeDoPrograma> <nomeDaPastaDeEntrada> <nomeDaPastaDeSaida> <numeroDeImagens> <numeroDeThreads>`

Em que:

    - <nomeDoPrograma> : Nome do arquivo .java que se deseja executar. Pode ser: compresSeq, compressDinamico ou compressBlocks
    
    - <nomeDaPastaDeEntrada> : Nome da pasta que armazena as imagens que desejamos comprimir. É necessário que essa pasta esteja localizada dentro na mesma pasta que temos os programas. Outra observação, é que as imagens armazenadas dentro desta página devem seguir uma nomenclatura padrão: `in (X)`, com X de 0 até o número total de imagem menos 1.
    
    - <nomeDaPastaDeSaida> : Nome da pasta na qual as imagens comprimidas serão armazenadas. Esta pasta também deve estar armazenada no mesmo diretório onde encontramos os programas.
    
    - <numeroDeImagens> : Número de imagens que desejamos comprimir
    
    - <numeroDeThreads> : Número de threads que serão utilizadas nos programas concorrentes

# ✅ Técnicas e tecnologias utilizadas

    - Java 8
    - Visual Code Studio
    - Orientação a objetos
    - Threads (Concorrência)

# Pessoas Desenvolvedoras do Projeto

Andrew da Silva Faria

Ana Carolina Ferreira de Figueiredo 
