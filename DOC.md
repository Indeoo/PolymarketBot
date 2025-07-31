docker run --name postgres-db \
-e POSTGRES_PASSWORD=mysecretpassword \
-e POSTGRES_USER=myuser \
-e POSTGRES_DB=mydatabase \
-p 5432:5432 \
-v pgdata:/var/lib/postgresql/data \
-d postgres
