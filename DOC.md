# API-Rest Curita

Es un sistema de administración para perfumerías que venden todo tipo de artículos conocídos cómo (Jabones, Shampoo para
el cabello, Cepillos de dientes, etc.).

## Use Case

![Entity relationship diagram for Curita API](/docs/usecase/usecase-curita-API.svg)

## Entity relationship diagram

![Entity relationship diagram for Curita API](/docs/diagram-bd/curita-API-DER.svg)

**Definition of tables**

***Article***

| Field Name  | Type                | Description       | Observation            |
|-------------|---------------------|-------------------|------------------------|
| id          | BIGINT AUTO_NUMERIC | Identify the item | primary key            |
| name        | VARCHAR(40)         | Item name         | Required               |
| stock       | INT                 | Item quantity     | required, >= 0         |
| price       | DECIMAL(5,2)        | Item price        | required, >= 0         |
| cost        | DECIMAL(5,2)        | Item cost         | required, >= 0         |
| barcode     | TEXT(20)            | Item barcode      | required               |
| provider_id | BIGINT              | Provider id       | required, relationship |
| section_id  | BIGINT              | Section id        | required, relationship |
| observation | TEXT(50)            | Item observation  |                        |

***Provider***

| Field Name       | Type                | Description       | Observation      |
|------------------|---------------------|-------------------|------------------|
| id               | BIGINT AUTO_NUMERIC | Identify the item | primary key      |
| name             | VARCHAR(25)         | Item name         | Required         |
| address          | VARCHAR(50)         | Item address      | required         |
| email            | VARCHAR(50)         | Item email        |                  |
| contact_provider | VARCHAR(30)         |                   |                  |
| phone            | VARCHAR(30)         | Item phone        | required         |
| cuil             | VARCHAR(20)         | Item cuil         | required, unique |
| start_operations | DATE                |                   | required         |
| observation      | TEXT(50)            | Item observation  |                  |

***Section***

| Field Name  | Type                | Description       | Observation |
|-------------|---------------------|-------------------|-------------|
| id          | BIGINT AUTO_NUMERIC | Identify the item | primary key |
| name        | VARCHAR(25)         | Item name         | Required    |
| observation | TEXT(50)            | Item observation  |             |