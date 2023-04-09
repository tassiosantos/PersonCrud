# PersonCrud
Simple CRUD to person and adress


Aplicação criada utilizando:
    java 11
    bando de dacos em memória



Para a aplicação rodar, com o maven (Apache Maven 3.6.3) e java 11:

No diretório "PersonCrud/target" rodar o comando: java -jar person-crud-0.0.1-SNAPSHOT.jar

É possível também rodar a aplicação, no diretório "PersonCrud" com o comando: mvn spring-boot:run

Para verificar o funcionamento da API, rodando o código localmente, a aplicação estará no endereço:
http://localhost:3000/

A aplicação estará rodando também na AWS:
http://ec2-18-117-192-35.us-east-2.compute.amazonaws.com:3000/

Funcionamento da API:

Tanto para o funcionamento local ou remoto, utilizar a url e completar com:
************************************************************
IMPORTANTE: 
Para cada uma das urls abaixo, quando tiver o ID na url, deve-se atribuir nesse campo algum ID.
Para cada uma das urls abaixo, quando tiver o AID na url, deve-se atribuir nesse campo algum ID de endereço.
************************************************************

- Criar uma pessoa:
	/pessoa/new

- Editar uma pessoa:
	/pessoa/ID

- Consultar uma pessoa:
	pessoa/ID

- Listar pessoas:
	pessoa/getAll

- Criar endereço para pessoa:
	pessoa/newAdress/ID

- Listar endereços da pessoa:
	pessoa/getAdresses/ID

- Informar qual endereço é o principal da pessoa:
	pessoa/getMainAdress/ID

- Alterar o endereço principal da pessoa
	pessoa/setMainAdress/ID/AID

************************************************************	
OBS:

- Como não foi especificado a necessidade do endereço na criação da pessoa, esse poderá ser adicionado após a criação desta pessoa.
- Se uma pessoa for criada sem endereço, quando o primeiro endereço dela for criado, ele será atribuído como o endereço principal dessa pessoa.
- Para que uma pessoa já criada possa ter seu endereço principal alterado, é necessário consultar os endereços e passar o ID do endereço que se deseja colocar como principal.

************************************************************
Formato do Json de endereço para se criar um novo endereço ou adicionar uma pessoa com endereço:
	{
		"logradouro":"rua xadsaasya",
		"cep":"4520158",
		"numero":"45A",
		"cidade":"vcashhhhaa"
	}

Formato do Json de pessoa para se criar uma nova pessoa sem endereço:

	{
		"id": 1,
		"nmPessoa": "tassio",
		"dtNascimento": "1988-05-16",
		"mainAdress": null
	}


Formato do Json de pessoa para se criar uma nova pessoa com endereço principal:

    {
        "nmPessoa": "tassio2",
        "dtNascimento": "1988-05-17",
        "mainAdress": {
                        "logradouro":"rua xadsaasya",
                        "cep":"4520158",
                        "numero":"45A",
                        "cidade":"vcashhhhaa"
                      }
    }


