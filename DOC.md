docker run --name postgres-db \
-e POSTGRES_PASSWORD=mysecretpassword \
-e POSTGRES_USER=myuser \
-e POSTGRES_DB=mydatabase \
-p 5432:5432 \
-v pgdata:/var/lib/postgresql/data \
-d postgres


docker run -d --name elasticsearch \
-p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-e "xpack.security.enabled=false" \
docker.elastic.co/elasticsearch/elasticsearch:8.13.4
