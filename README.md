# Spring Boot Microservices Deployment with Minikube

This repository contains the deployment setup for four Spring Boot microservices (`/hello`, `/world`, `/helloworld` and `config-server`) on a **Minikube** Kubernetes cluster.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Docker Image Creation](#docker-image-creation)
- [Kubernetes Deployment with Minikube](#kubernetes-deployment-with-minikube)
- [Accessing the Services](#accessing-the-services)
- [Cleanup](#cleanup)

## Prerequisites

Before proceeding with the deployment, ensure you have the following installed:

1. [Docker](https://docs.docker.com/get-docker/) - for building Docker images.
2. [Minikube](https://minikube.sigs.k8s.io/docs/start/) - for running a local Kubernetes cluster.
3. [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/) - command-line tool for Kubernetes.
4. [Java 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - to run Spring Boot services.

## Project Structure

```bash
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
|
├── hello-world-service/                # Hello World Service microservice
│   ├── src/                      # Hello World Service source code
│   ├── Dockerfile                # Dockerfile for Hello World Service
│   ├── hello-world-service-deployment.yml
│   └── hello-world-service-service.yml
└── ingress.yml                   # Ingress configuration for Minikube

```

## Docker Image Creation

Step 1: Start Minikube and Enable Docker Environment

Start Minikube and set the Docker environment to use Minikube’s Docker daemon.

```bash
minikube start
eval $(minikube docker-env)
```

## Step 2: Build Docker Images

Navigate to each service directory (config-server, hello-service, world-service) and build the Docker images:

```bash

# Build Config Server Docker image
cd config-server
docker build -t config-server-service:latest .

# Build Hello Service Docker image
cd ../hello-service
docker build -t hello-service:latest .

# Build World Service Docker image
cd ../world-service
docker build -t world-service:latest .

# Build Hello World Service Docker image
cd ../hello-world-service
docker build -t hello-world-service:latest .
```

Verify the Docker images:

```bash

docker images
```

Docker Hub Link :
1. [config-server-service image](https://hub.docker.com/repository/docker/meet1699/config-server-service) - Config Server Service Image.
2. [hello-service image](https://hub.docker.com/repository/docker/meet1699/hello-service) - Hello Service Image.
3. [world-service image](https://hub.docker.com/repository/docker/meet1699/world-service) - World Service Image.
4. [hello-world-service image](https://hub.docker.com/repository/docker/meet1699/hello-world-service) - Hello World Service Image.



## Kubernetes Deployment with Minikube

Step 1: Deploy Config Server

Apply the deployment and service YAML files for the Config Server:

```bash

kubectl apply -f config-server/config-server-deployment.yml
kubectl apply -f config-server/config-server-service.yml
```

Step 2: Deploy Hello Service

Deploy the Hello Service and link it with the Config Server:

```bash

kubectl apply -f hello-service/hello-service-deployment.yml
kubectl apply -f hello-service/hello-service-service.yml
```

Step 3: Deploy World Service

Deploy the World Service in the same manner:

```bash

kubectl apply -f world-service/world-service-deployment.yml
kubectl apply -f world-service/world-service-service.yml
```

Step 4: Deploy Hello World Service

Deploy the Hello World Service in the same manner:

```bash

kubectl apply -f hello-world-service/hello-world-service-deployment.yml
kubectl apply -f hello-world-service/hello-world-service-service.yml
```

Step 5: Ingress Configuration

Minikube supports the use of an ingress controller to route external traffic to your services. First, enable the ingress addon:

```bash

minikube addons enable ingress
```

Apply the ingress configuration file:

```bash

kubectl apply -f ingress.yml
```

Step 6: Verify Deployments and Services

Check the status of the pods and services:

```bash

# View the status of the pods
kubectl get pods

# View the status of the services
kubectl get svc
```

## Accessing the Services

Step 1: Get Minikube IP

Use the following command to get Minikube’s IP address:

```bash

minikube ip
```

Step 2: Test Services

Access the services by visiting the following URLs in your browser (replace <minikube-ip> with the IP from the previous command):

    Hello Service: http://<minikube-ip>/hello
    World Service: http://<minikube-ip>/world
    Hello World Service: http://<minikube-ip>/helloworld

Alternatively, you can use kubectl port-forwarding to access individual services:

```bash

# Port forward Config Server
kubectl port-forward svc/config-server-service 8088:8088

# Port forward Hello Service
kubectl port-forward svc/hello-service 8081:8081

# Port forward World Service
kubectl port-forward svc/world-service 8082:8082

# Port forward Hello World Service
kubectl port-forward svc/hello-world-service 8083:8083
```

## Cleanup

To delete all the Kubernetes resources (deployments, services, ingress), run the following command:

```bash

kubectl delete -f config-server-service/
kubectl delete -f hello-service/
kubectl delete -f world-service/
kubectl delete -f hello-world-service/
kubectl delete -f ingress.yml
```

To stop Minikube, run:

```bash

minikube stop
```

To delete Minikube, run:

```bash

minikube delete
```

## Conclusion

This project demonstrates how to deploy Spring Boot microservices using Minikube for local Kubernetes development. The Config Server provides externalized configuration to the Hello and World services, and all services are exposed through an Ingress.

