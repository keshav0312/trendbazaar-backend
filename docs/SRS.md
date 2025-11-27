# SRS - Trendbazaar (Simple)

## Project overview
Trendbazaar is an e-commerce backend that allows admins to manage products and users to view products and place orders.


## Actors
- User
- Admin

## Functional requirements
1. User registration/login (JWT) - later
2. Product CRUD (Admin)
3. Product listing (public)
4. Cart & Orders - later
5. Payment integration - later

## Acceptance criteria (Week-1)
- Admin can create product via POST /api/products
- GET /api/products returns list

## Non-functional
- DB: H2 for dev
- Logging + Global exception handler
