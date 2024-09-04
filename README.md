Spring Boot Microservices Deployment with Minikube

This repository contains the deployment setup for three Spring Boot microservices (/hello, /world, and config-server) on a Minikube Kubernetes cluster.
Table of Contents

    Prerequisites
    Project Structure
    Docker Image Creation
    Kubernetes Deployment with Minikube
    Accessing the Services
    Cleanup

Prerequisites

Before proceeding with the deployment, ensure you have the following installed:

    Docker - for building Docker images.
    Minikube - for running a local Kubernetes cluster.
    kubectl - command-line tool for Kubernetes.
    Java 11+ - to run Spring Boot services.

Project Structure

bash

your-project/
│
├── config-server/                # Config Server microservice
│   ├── src/                      # Config Server source code
│   ├── Dockerfile                # Dockerfile for Config Server
│   ├── config-server-deployment.yml
│   └── config-server-service.yml
│
├── hello-service/                # Hello Service microservice
│   ├── src/                      # Hello Service source code
│   ├── Dockerfile                # Dockerfile for Hello Service
│   ├── hello-service-deployment.yml
│   └── hello-service-service.yml
│
├── world-service/                # World Service microservice
│   ├── src/                      # World Service source code
│   ├── Dockerfile                # Dockerfile for World Service
│   ├── world-service-deployment.yml
│   └── world-service-service.yml
└── ingress.yml                   # Ingress configuration for Minikube

Docker Image Creation
Step 1: Start Minikube and Enable Docker Environment

Start Minikube and set the Docker environment to use Minikube’s Docker daemon.

bash

minikube start
eval $(minikube docker-env)

Step 2: Build Docker Images

Navigate to each service directory (config-server, hello-service, world-service) and build the Docker images:

bash

# Build Config Server Docker image
cd config-server
docker build -t config-server:latest .

# Build Hello Service Docker image
cd ../hello-service
docker build -t hello-service:latest .

# Build World Service Docker image
cd ../world-service
docker build -t world-service:latest .

Verify the Docker images:

bash

docker images

Kubernetes Deployment with Minikube
Step 1: Deploy Config Server

Apply the deployment and service YAML files for the Config Server:

bash

kubectl apply -f config-server/config-server-deployment.yml
kubectl apply -f config-server/config-server-service.yml

Step 2: Deploy Hello Service

Deploy the Hello Service and link it with the Config Server:

bash

kubectl apply -f hello-service/hello-service-deployment.yml
kubectl apply -f hello-service/hello-service-service.yml

Step 3: Deploy World Service

Deploy the World Service in the same manner:

bash

kubectl apply -f world-service/world-service-deployment.yml
kubectl apply -f world-service/world-service-service.yml

Step 4: Ingress Configuration

Minikube supports the use of an ingress controller to route external traffic to your services. First, enable the ingress addon:

bash

minikube addons enable ingress

Apply the ingress configuration file:

bash

kubectl apply -f ingress.yml

Step 5: Verify Deployments and Services

Check the status of the pods and services:

bash

# View the status of the pods
kubectl get pods

# View the status of the services
kubectl get svc

Accessing the Services
Step 1: Get Minikube IP

Use the following command to get Minikube’s IP address:

bash

minikube ip

Step 2: Test Services

Access the services by visiting the following URLs in your browser (replace <minikube-ip> with the IP from the previous command):

    Config Server: http://<minikube-ip>:8888
    Hello Service: http://<minikube-ip>/hello
    World Service: http://<minikube-ip>/world

Alternatively, you can use kubectl port-forwarding to access individual services:

bash

# Port forward Config Server
kubectl port-forward svc/config-server 8888:8888

# Port forward Hello Service
kubectl port-forward svc/hello-service 8081:8081

# Port forward World Service
kubectl port-forward svc/world-service 8082:8082

Cleanup

To delete all the Kubernetes resources (deployments, services, ingress), run the following command:

bash

kubectl delete -f config-server/
kubectl delete -f hello-service/
kubectl delete -f world-service/
kubectl delete -f ingress.yml

To stop Minikube, run:

bash

minikube stop

To delete Minikube, run:

bash

minikube delete

Conclusion

This project demonstrates how to deploy Spring Boot microservices using Minikube for local Kubernetes development. The Config Server provides externalized configuration to the Hello and World services, and all services are exposed through an Ingress.
