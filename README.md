# Backend

## Requisições

```shell
$ curl -X POST http://localhost:8080/v1/campeonatos \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{"ano": 2003, "numeroParticipantes": 24, "dataInicio": "2003-03-29", "dataTermino": "2003-12-19"}' | jq
```
