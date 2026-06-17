*JOGO DE FAZENDEIRO - HAPPY FARM*

*INFORMAÇÕES - ALUNOS:*
- João Daniel Würdig Lucas (202510515)
- Gabriel Maroneze Ramos (202512445)
- Curso: Sistemas de Informação

*INFORMAÇÕES - DISCIPLINA:*
- Docente: Andrea Schwertner Charão
- Disciplina: Paradigmas de Programação

*INFORMAÇÕES CONCEITUAIS:*
- O projeto consiste em um jogo single-player com a temática de fazenda (semelhante à jogos conhecidos como Stardew Valley e Harvest Moon).
- Os objetivos do jogador serão plantar sementes e cuidar de suas plantas de acordo com as necessidades de cada uma. Após a colheita, ele poderá fazer receitas com os insumos coletados e vendê-las para somar pontos e desbloquear novas hortaliças/receitas.


*PROGRESSO*

- Dia 28/05/2026: Consegui instalar o libGDX em minha máquina e criar um projeto por lá. Não consegui adicionar os arquivos gerados no diretório do trabalho e fazer o commit corretamente. (Gabriel)

- Dia 29/05, 09:24 da manhã: Ontém após a aula, conversei com o Cadu e utilizei o Claude.AI para aprender a unir os arquivos gerados e realizar o commit no github. Consegui colocar em prática agora de manhã. Não sabia bem quais extensões do libGDX incluir, mas selecionei um número elevado cujos títulos tinham relação com jogos 2D ou me pareciam úteis.
Agora, devo usar o Claude para ter um direcionamento sobre como iniciar o projeto, prentendo aprender como construir um cenário base e incluir um personagem com movimentações funcionais e colisão, para testes. (Gabriel)

- Dia 01/06: decidimos que o Gabriel vai ficar procurando sprites para o nosso game e eu vou focar em fazer funcionar os exemplos básicos da aula e organizar a relação de classes do nosso programa. (João)
- Dia 01/06, 16:40: decidimos colocar o nome do bonequinho da sprite que o Gabriel pegou de Alfredo. (João)
- Dia 01/06, 18:00: vimos algumas sprites promissoras com um boneco golpeando com espada, achamos que, se editarmos o arquivo num editor de pixels, podemos mudar essa espada para as nossas ferramentas. (João)
- Dia 06/06: depois de pensarmos sobre, conseguimos chegar num esquema de como vai ser o jogo e os objetos que iremos usar (10 plantas, 5 ferramentas e 15 receitas). As plantas vão ter o estado de aguardar ferramenta, crescendo e pronta pra colher, e as ferramentas vão ser divididas entre as que são usadas antes e depois do plantio. (João)

- Dia 07/06: entre os objetos que estou fazendo, tem alguns que podem ser tanto itens para venda quanto ingredientes (ex.: farinha), então estou em dúvida no que eu faço. (João)
- Dia 10/06: resolvi usar enums para ser como uma lista de quais nomes ão ingredientes, quais são receita e por aí vai, acredito q por agora é uma solução boa. (João)
- Dia 10/06: O Claude.AI me deu algumas possíveis soluções para os problemas com colisões que estava enfrentando usando o Tiled. Removi todas as anteriores e tentarei arrumar (Gabriel)

- Dia 12/06: Criei um contador de colisões que mostra quantas foram carregadas no terminal, para testes. (Gabriel)
- Dia 12/06: Importei uma biblioteca que permite a escrita de caracteres na tela do jogo para rastrear a posição do jogador e encontrar um ponto de spawn dentro do cenário. (Gabriel)
- Dia 12/06: Alterei o ponto de spawn do jogador. (Gabriel)
- Dia 12/06: Criei um novo diretório no libGDX sem as extensões desnecessárias que davam conflito ao tentar rodar o jogo via web. O projeto rodou sem erros, mas não consegui abrir o jogo de fato, nem gerar arquivos. Irei aprender como prosseguir daqui. (Gabriel)
- Dia 12/06: Terminei a lógica das classes e criei uma pasta registry com criação de objetos, acredito que a partir daí conseguimos mexer neles dentro do jogo. (João)
