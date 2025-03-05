<h1 align="center" style="font-weight: bold;">Time Sheet Control 💻</h1>

<p align="center">
 <a href="#tech">Tecnologias</a> • 
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> •
 <a href="#colab">Collaborators</a> •
 <a href="#contribute">Contribute</a>
</p>

<p align="center">
    <b>Este projeto é um sistema para controle de folha de ponto, desenvolvido utilizando Java com Spring Boot. Ele oferece autenticação via JWT e OAuth com Spring Security e utiliza PostgreSQL como banco de dados.</b>
</p>

<h2 id="technologies">💻 Technologies</h2>

- Java 21
- Spring Boot
- Spring Security (JWT e OAuth)
- PostgreSQL
- JPA/Hibernate
- Flyway
- iTextPDF (iText7 Core) para geração de relatórios em PDF

<h2 id="started">🚀 Getting started</h2>

<h3>Pré-requisitos</h3>

- Java 17+
- PostgreSQL instalado e configurado
- Maven

<h3>Cloning</h3>

```bash
git clone https://github.com/Gabryel-Januario/time-sheet-control
```

<h3>Config .properties variables</h2>


```yaml
spring.datasource.url=[your-database-URL]
spring.datasource.username=[your-username]
spring.datasource.password=[your-password]

api.security.token.secret=${SECRET_KEY}

spring.security.oauth2.client.registration.google.client-id=[your-google-client-id]
spring.security.oauth2.client.registration.google.client-secret=[your-google-client-secret]
```

<h3>Starting</h3>

```bash
cd project-name
mvn spring-boot:run
```

<h2 id="routes">📍 API Endpoints</h2>

​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /auth/login</kbd>     | Login do usuário retornando token 
| <kbd>POST /auth/register</kbd>     | registro de usuário 
| <kbd>POST /oauth2/authorization/google</kbd>     | autentificação via Oauth2 
| 
| <kbd>GET /users/all</kbd>     | Visualiza todos usuários 
| <kbd>GET /users/user/{id}</kbd>     | Visualiza usuário pelo id 
| <kbd>PUT /users/user/{id}</kbd>     | Atualiza os dados do usuário 
| <kbd>DELETE /users/user/{id}</kbd>     | Deleta o usuário 
| 
| <kbd>POST /record/check_in</kbd>     | Realiza check in 
| <kbd>POST /record/check_out/{id}</kbd>     | Realiza check out 
| <kbd>GET /record/all</kbd>     | Visualiza todos os pontos 
| <kbd>GET /record/my</kbd>     | Visualiza os pontos do usuário 
| <kbd>GET /record/employee/{id}</kbd>     | Visualiza os pontos do usuário pelo id 
| <kbd>GET /record/employee/{id}/pdf</kbd>     | Cria um relatório em pdf 


<h2 id="colab">🤝 Colaborador</h2>

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/161720296?s=96&v=4" width="100px;" alt="Gabryel Januario Profile Picture"/><br>
        <sub>
          <b>Fernanda Kipper</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

<h2 id="contribute">📫 Contribute</h2>

Sinta-se à vontade para abrir issues e pull requests para melhorias!

