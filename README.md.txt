ClientRepository: 

Encontrar clientes por:
- nome começando por com ignore case,
- encontrar por cpf do cliente, 
- encontrar por email do cliente,
- listar todos os clientes em ordem A-Z.
- por id.

AlarmRepository:

Encontrar alarmes por: 
- nome começando por,
- tipo de alarme startingwithignorecase,
- preco do alarme entre,
- preco do alarme,
- preco da camera menor que,
- preco da camera maior que,
- listar todos os nomes de alarmes em ordem A-Z.
- por id.

CameraRepository:

Encontrar cameras por:
- nome começando por,
- tipo de camera startingwithignorecase,
- preco da camera entre,
- preco da camera,
- preco da camera menor que,
- preco da camera maior que,
- listar todos os nomes de cameras em ordem A-Z.
- por id.


StockRepository:

Encontrar estoque de produtos por:
- Produtos que estão com menos de 5 em estoque,
- Quais produtos possuem maior em estoque,
- Quais produtos possuem menor em estoque,
- listar todos os estoques
- por id.

AdressRepository:
Encontrar endereço por:
- Endereços de clientes que começam com a rua tal começando por com ignorecase,
- Clientes que possuem Endereços localizados no bairro tal,
- Clientes que possuem numero da casa tal,
- Clientes da cidade tal, 
- Clientes do estado tal,
- listar todos os endereços,
- por id.

ContractRepository: 

Encontrar contrato por:
- Datas entre
- Data de inicio
- Data de fim
- valor do contrato 
- puxar contrato de clientes.
- listar todos contratos 
- por id.

EmailRepository:

Encontrar clientes by email:
- Email que começa por 
- id do cliente daquele email.
- listar todos emails
- por id.

ProductRepository:
Encontrar produtos por:
- Nome com ignore case
- tipo de produto, camera ou alarme
- valor do produto
- listar todos os produtos
- por id.

TelephoneRepository
- nome de clientes que possuem tal telefone
- por id
- listar todos os telefones
- telefone que começa por

SQL:
CLIENT
insert into client(id,name,cpf,password) values (3,'Danielle','876.543.210-01','123');
insert into client(id,name,cpf,password) values (4,'Clavison','259.683.417-93','123');
insert into client(id,name,cpf,password) values (5, 'Cauã', '749.105.623-80','123');
insert into client(id,name,cpf,password) values (6, 'Tais', '384.916.752-26','123');
insert into client(id,name,cpf,password) values (7, 'Gabrielly', '610.497.835-44','123');
insert into client(id,name,cpf,password) values (8, 'Bianca', '978.305.426-68','123');
insert into client(id,name,cpf,password) values (9, 'Alexandre', '163.874.592-10','123');
insert into client(id,name,cpf,password) values (10,'Lucas', '532.796.841-53','123');
insert into client(id,name,cpf,password) values (11, 'Max','712.439.058-30','123');
insert into client(id,name,cpf,password) values (12, 'Gabriela','857.219.603-29','123');

