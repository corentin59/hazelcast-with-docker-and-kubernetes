## FLASH30 : Hazelcast with Docker and Kubernetes on Google Cloud Container ##

### Sample
This is a web application to display [V'Lille](http://vlille.fr/stations/les-stations-vlille.aspx) bikes stations in a browser.
Hazelcast is used for cache, we use hazelcast-kubernetes-discovery for pods discovery (since Hazelcast 3.6).

### Slides
Tomorrow !

### Build
[![Build Status](https://api.travis-ci.org/corentin59/hazelcast-with-docker-and-kubernetes.png)](https://travis-ci.org/corentin59/hazelcast-with-docker-and-kubernetes)

### Technical
* Tomcat 8 is the servlet container
* Java is the language
* Docker and Kubernetes for deployment
* Maven for dependencies
* Sprint Boot for micro container
* AngularJS for front
* Hazelcast for cache and data replication

### Video
See here in HD : https://www.youtube.com/watch?v=ZjVvddVGSbU

### Quick Boot
 1. Copy vlille.rc.json and vlille.service.json on a server with kubectl
 2. Create a cluster : gcloud container clusters create cluster-vlille --num-nodes 3 --machine-type f1-micro --zone europe-west1-b --no-enable-cloud-logging --no-enable-cloud-monitoring
 2. Start rc : kubectl create -f vlille.rc.json
 3. Start service (load balancer) : kubectl create -f vlille.service.json
 4. Wait... and open the loadbalancer ip : kubectl get services

### Boot

#### 1. Build image :
The build is provided by Travis
https://travis-ci.org/corentin59/hazelcast-with-docker-and-kubernetes/

#### 2. Docker Hub :
You can pull the image from Docker hub
https://hub.docker.com/r/corentin59/hazelcast-with-docker-and-kubernetes/

#### 3. Install kubectl (Debian) :
The Google Container Engine quick start to install
https://cloud.google.com/container-engine/docs/before-you-begin

Install GCloud :
> apt-get install curl
> curl https://sdk.cloud.google.com | bash
> exec -l $SHELL
> gcloud init
> gcloud config set compute/zone europe-west1-b
> gcloud container clusters get-credentials cluster-vlille (please create a cluster before)

Install Kubernetes on GCloud :
> gcloud components update kubectl

#### 4. Launch from kubectl (Kubernates) :

Copy kubernetes/* files on your kubectl master service

Create cluster
> gcloud container clusters create cluster-vlille --num-nodes 3 --machine-type f1-micro --zone europe-west1-b --no-enable-cloud-logging --no-enable-cloud-monitoring

Launch the replica controller
> kubectl create -f vlille.rc.json

Create service
> kubectl create -f vlille.service.json

View pods status
> kubectl get pods -o wide

View replica status
> kubectl get rc vlille

View service status
> kubectl get service vlille

Resize
> kubectl scale rc vlille --replicas=3

Destroy service
> kubectl stop service vlille

Destroy rc
> kubectl stop rc vlille

## Thanks !
* Noctarius : https://github.com/noctarius/hazelcast-kubernetes-discovery
* Ray Tsang : https://www.youtube.com/watch?v=kT1vmK0r184

## License

```
            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
                    Version 2, December 2004

 Copyright (C) 2015 <corentin@azelart.fr>

 Everyone is permitted to copy and distribute verbatim or modified
 copies of this license document, and changing it is allowed as long
 as the name is changed.

            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

  0. You just DO WHAT THE FUCK YOU WANT TO.
```

```
 This program is free software. It comes without any warranty, to
 the extent permitted by applicable law. You can redistribute it
 and/or modify it under the terms of the Do What The Fuck You Want
 To Public License, Version 2, as published by Corentin A. See
 http://www.wtfpl.net/ for more details.
```
