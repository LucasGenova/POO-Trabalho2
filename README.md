# Trabalho 2

Você foi incumbindo de criar um sistema de vendas de veículos para Concessionária José Motors.  
Data de entrega: 17/07/2022 
Integrantes: 2 a 4 
 
## Especificações: 

O sistema deverá permitir o cadastro, alteração e exclusão das classes: 
- Funcionário: RG, nome, Data de nascimento, Data de admissão, Salário
    - Vendedor: tempo de treinamento restante, Gerente responsável 
    - Gerente: anos de experiência. 
- Veículo: Número do chassi, marca, modelo, ano, quilometragem, tipo de combustível, peso, status (vendido, a venda). 
    - Carro: potência, número de cilindros, número de ocupantes, tipo (utilitário, pickup, sedan, hatch, esportivo), dimensões (altura, largura e comprimento). 
    - Motocicleta: cilindradas, tipo(trail, street, esportiva, custom) 
 
- Cliente: CPF, nome, Data de nascimento, endereço (rua, número, bairro e cidade), 
renda, dependentes. 
- Venda: ID da venda, Vendedor, Cliente, Veículo, valor, Data, Horário 

## Observações 
- Gerente é o único tipo de usuário que poderá cadastrar, alterar e excluir novos Clientes, Veículos e Funcionários. 
- O sistema será utilizado apenas para os funcionários. 
- É necessário um mecanismo de login de modo a identificar o tipo de funcionário. 
- As classes Veículo e Funcionário não poderão ser instanciadas diretamente. 
- Data: classe implementada, o dia (1 a 31), mês (1 a 12) e ano (1950 a 2050) deverão ser tratados para evitar a inserção de dados inválidos. 
- Horário: classe implementada, hora (0 a 23) e minuto (0 a 59) deverão ser tratados para evitar a inserção de dados inválidos. 
- Utilize ArrayLists para as coleções dos objetos das classes. 
- Quando um veículo é vendido seu status é alterado de “a venda” para “vendido”. 
- Salvamento em arquivos:  Os dados cadastrados deverão ser salvos em diferentes arquivos para possibilitar a recuperação de dados após o fechamento do programa. 
-  Os Vendedores terão acesso as informações sobre os Veículos, Clientes, Vendas. 
-  O Gerente terá acesso similar à Vendedores, com o acesso adicional sobre o desempenho dos Vendedores(quantidade de vendas realizadas, se foram à prazo ou à vista, tipo de carro mais vendido). 
- Utilize métodos get e set, construtores com argumentos, herança e outros conceitos vistos em POO. 
-  OPCIONAL: Uma interface gráfica pode ser implementada. Neste caso será acrescentado 0,5 ponto à nota geral do trabalho. 
 
## Entregáveis: 
- Arquivos .java  
- Caso optem por utilizar interface gráfica, enviem também o projeto no Netbeans

## O que será avaliado 
- A apresentação do programa (menus, forma de exibição dos dados, indentação e organização do código). 
- O cumprimento dos requisitos exigidos na especificação. 
- Uso dos conceitos aprendidos na disciplina. 
 
Bom trabalho!