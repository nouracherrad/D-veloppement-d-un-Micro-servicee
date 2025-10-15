
 # D-veloppement-d-un-Micro-servicee

Ce projet est un microservice Spring Boot permettant de gérer des comptes bancaires. Il implémente plusieurs approches d'API (REST, GraphQL) et utilise différentes technologies Spring.

##  Table des Matières

1. [Architecture du Projet](#architecture)
2. [Technologies Utilisées](#technologies)
3. [Structure du Projet](#structure)
4. [Implémentation](#implementation)
5. [API Endpoints](#api)
6. [Tests](#tests)
7. [GraphQL](#graphql)

##  Architecture du Projet <a name="architecture"></a>

Le projet suit une architecture en couches classique :
- **DTO** : Objets de transfert de données
- **Entities** : Entités JPA
- **Repositories** : Couche d'accès aux données
- **Service** : Couche métier
- **Web** : Contrôleurs REST et GraphQL
- **Mappers** : Conversion entre entités et DTOs

##  Technologies Utilisées <a name="technologies"></a>

- **Spring Boot 3.x**
- **Spring Data JPA** - Persistance des données
- **H2 Database** - Base de données en mémoire
- **Lombok** - Réduction du code boilerplate
- **Spring Web** - API REST
- **Spring Data REST** - API REST automatique
- **GraphQL** - API GraphQL
- **Swagger/OpenAPI** - Documentation des APIs

##  Structure du Projet <a name="structure"></a>

```
org.sdia.bankaccountservice
├── DTO
│   ├── BankAccountRequestDTO
│   └── BankAccountResponseDTO
├── entities
│   ├── AccountProjection
│   ├── BankAccount
│   └── Customer
├── enums
│   └── AccountType
├── exceptions
│   └── CustomDataFetchExceptionResolve
├── Mappers
│   └── AccountMapper
├── repositories
│   ├── BankAccountRepository
│   └── CustomerRepository
├── service
│   ├── AccountService
│   └── AccountServiceImpl
└── web
    ├── AccountRestController
    ├── BankAccountGraphqlController
    └── BankAccountServiceApplication
```

##  Implémentation 

### 1. Création du Projet Spring Boot

Créé avec Spring Initializr avec les dépendances :
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- GraphQL Spring Boot Starter

### 2. Entité JPA BankAccount

```java
@Entity
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    
    @ManyToOne
    private Customer customer;
    // Getters, Setters, Constructeurs
}
```

### 3. Enum AccountType

```java
public enum AccountType {
    CURRENT_ACCOUNT, SAVING_ACCOUNT
}
```

### 4. Repository Spring Data

```java
@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByCustomerId(Long customerId);
}
```

### 5. DTOs et Mappers

**BankAccountRequestDTO** : Pour les requêtes de création
**BankAccountResponseDTO** : Pour les réponses

**AccountMapper** : Utilise MapStruct pour mapper entre entités et DTOs

### 6. Couche Service

**AccountService** : Interface définissant les opérations métier
**AccountServiceImpl** : Implémentation des services

### 7. Contrôleurs

**AccountRestController** : API REST traditionnelle
**BankAccountGraphqlController** : API GraphQL

##  API Endpoints <a name="api"></a>

### API REST

- `GET /api/accounts` - Lister tous les comptes
- `GET /api/accounts/{id}` - Obtenir un compte par ID
- `POST /api/accounts` - Créer un nouveau compte
- `PUT /api/accounts/{id}` - Modifier un compte
- `DELETE /api/accounts/{id}` - Supprimer un compte

### Spring Data REST

- `GET /bankAccounts` - Liste paginée des comptes
- `GET /bankAccounts/{id}` - Détail d'un compte
- `POST /bankAccounts` - Création via Spring Data REST
- 
###### voila un extrait de b ase de donnes sur H2
<img width="1117" height="833" alt="image" src="https://github.com/user-attachments/assets/c48223b1-1bd4-4742-9b05-f9a8dc23e70f" />

##  Tests <a name="tests"></a>

### Test de la Couche DAO

```java
@DataJpaTest
class BankAccountRepositoryTest {
    @Autowired
    private BankAccountRepository repository;
    
    @Test
    void shouldFindAccountsByCustomer() {
        // Tests des méthodes de repository
    }
}
```

### Tests avec Postman

Collections Postman disponibles pour tester :
- Création de compte
- Consultation
- Modification
- Suppression

##  GraphQL <a name="graphql"></a>

### Schéma GraphQL (`schema.graphqls`)

```graphql
type BankAccount {
    id: ID!
    createdAt: String!
    balance: Float!
    type: AccountType!
    customer: Customer!
}

type Customer {
    id: ID!
    name: String!
    email: String!
}

type Query {
    accountList: [BankAccount]
    accountById(id: ID!): BankAccount
}

type Mutation {
    createAccount(account: BankAccountInput!): BankAccount
}
```
<img width="1716" height="617" alt="image" src="https://github.com/user-attachments/assets/d2bc0a45-8a02-4778-889e-4d4e34d5e7de" />
<img width="1696" height="658" alt="image" src="https://github.com/user-attachments/assets/2b92e699-b6fd-4aed-b7b0-764b3ad0c28b" />


### Requêtes GraphQL Exemple

**Récupérer tous les comptes :**
```graphql
query {
  accountList {
    id
    balance
    type
    customer {
      name
    }
  }
}
```

**Créer un compte :**
```graphql
mutation {
  createAccount(account: {
    balance: 1000.0
    type: CURRENT_ACCOUNT
    customerId: 1
  }) {
    id
    balance
  }
}
```

##  Documentation Swagger

La documentation Swagger est accessible à :
```
http://localhost:8080/swagger-ui.html
```
### voila un extrait de test sur swagger 
<img width="1790" height="933" alt="image" src="https://github.com/user-attachments/assets/74b86dd7-1b6a-453d-825b-a179c542a439" />

##  Démarrage

1. Cloner le projet
2. Importer dans l'IDE
3. Lancer l'application
4. Accéder à :
   - Application : http://localhost:8080
   - H2 Console : http://localhost:8080/h2-console
   - Swagger : http://localhost:8080/swagger-ui.html
   - GraphQL : http://localhost:8080/graphiql

## Configuration

Le fichier `application.properties` contient la configuration de la base H2 et des paramètres GraphQL.




