## CAPGEMINI FLASH30 : Hazelcast with Docker and Kubernetes ##
Session start : 08.11.2015 @ 13:30-14:00

### Sample
This is a web application for display V'Lille stations in a browser.

### Technical
* Tomcat 8 is the servlet container
* Java is the language
* Docker and Kubernetes for deployment
* Maven for dependencies
* Sprint Boot for micro container
* AngularJS for front
* Hazelcast for cache and data replication

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

Install gcp-live-k8s-visualizer
> apt-get install git
> git clone https://github.com/brendandburns/gcp-live-k8s-visualizer.git
> cd gcp-live-k8s-visualizer
> kubectl proxy -p 8080 -w .
You can now open this page : http://127.0.0.1/static/

#### 3. Launch from kubectl (Kubernates) :

Launch your latest version on 1 pod
> kubectl run vlille --image=corentin59/hazelcast-with-docker-and-kubernetes:latest

View pods status
> kubectl get pods -o wide

Expose
> kubectl expose rc vlille --port=80 --target-port=8000


#### 4. Display webapp :


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
