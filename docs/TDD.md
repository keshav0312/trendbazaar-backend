# TDD Plan - Trendbazaar

## What is TDD?
1. Write a failing test.
2. Implement code to pass test.
3. Refactor.

## Week-1 tests (priority)
- ProductController.create -> returns 201 and product id
- ProductController.getAll -> returns 200 and list
- ProductService.create -> product saved in DB

## How to run tests
- `mvn test`
