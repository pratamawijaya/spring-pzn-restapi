echo "=======springboot build======="
gradle build

echo "=======docker build running======="
docker build --tag kotlin-restful-api:1.0.0 .

echo "=======docker-compose running======="
docker-compose -f docker-compose.yml up -d