# Selenoid_demo




Selenoid documentation (Mostly covers windows – Change the File separator for linux or Mac accordingly):
Overview : 
Selenoid can be considered as a replacement to selenium grid. It is possible for us to setup selenium grid in local or in docker using images like selenium/hub and browser images.
Scope of this document :
I have restricted the scope of this document to cover how to setup solenoid in docker. 
Steps for setting Selenoid in docker:
1.	Creating the browsers.json file in a folder call config.
2.	Pull the images required from docker hub.
3.	Spinning up the containers with selenoid , selenoid-ui, selenoid/video-recorder images
4.	Creating tests
5.	Viewing live preview
6.	Accessing videos
7.	Deleting videos


1.	Creating the browsers.json file:
browsers.json seems to be heart of selenoid. Selenoid parse this json file and understand the possible combinations to run the tests. If the user has specified specific capabilities then it will be considered otherwise the default will be chosen.
For eg) A typical browsers.json file will look something like
 
If the user have not mentioned some specific capability like browser version, then it will run the test on the browser version where default is mentioned.
Note: User have to create this document in the local host manually. The images mentioned in this file will not downloaded from docker-hub automatically.  We need to specifically pull the browser , android images from dockerhub separately.
In my case I have created the file in the path :  
2.	Pull the images from docker hub.
a)	docker pull aerokube/selenoid:latest-release or docker pull aerokube/selenoid:latest
b)	docker pull aerokube/cm:latest-release or docker pull aerokube/cm:latest
c)	docker pull aerokube/selenoid-ui
d)	docker pull selenoid/video-recorder:latest
e)	selenoid/vnc_chrome:84 and whatever the version you need. 
f)	docker pull selenoid/vnc_firefox and whatever the version you need. (VNC enabled browsers for live preview of execution. You don’t need this vnc for using video recording feature)
g)	Selenoid/android and whatever the version you need(It can be only run on machines where /dev/kvm is enabled ie – On a linux server or VM with nested virtualization enabled. This is because of the limitations with docker)













3.	Spinning up the containers with selenoid , selenoid-ui, selenoid/video-recorder images
Execute the following commands:
1.	When I opened the powershell, I should be in a location where the config folder is present where we kept the browsers.json file. Selenoid will by default look for browsers.json file in the /config/browsers.json location. (You can customize this by passing the – config parameter with value of json location while spinning the aerokube/selenoid:tag)  

b)	$current = $PWD -replace "\\", "/" -replace "C", "c" (to get the current directory location – only works in powershell) 🡪 Not a mandatory stuff

c)	docker run -d --name selenoid -p 4444:4444 -v //var/run/docker.sock:/var/run/docker.sock -v ${current}/config/:/etc/selenoid/:ro -v /c/Users/asakthiv/selenoid/video/:/opt/selenoid/video/ -e OVERRIDE_VIDEO_OUTPUT_DIR=/c/Users/asakthiv/selenoid/video/ aerokube/selenoid:latest-release  (will create selenoid container with video recording enabled. Videos will be stored to /c/users/asakthiv/selenoid. Please give the absolute path when overriding the video output dir parameter. Docker will not create the folder by itself. Folders needs to be created and mounted accordingly)

d)	Point your tests to http://localhost:4444/wd/hub/ (If you are setting this in any other machine – use the machine ip instead of localhost)

e)	docker run --rm -d --name selenoid-ui --link selenoid -p 8090:8080 aerokube/selenoid-ui --selenoid-uri=http://selenoid:4444  (To view the live execution : http://localhost:8090/ - same as point d)

f)	The above commands may slightly vary for linux or Mac due to file separators(Please change it accordingly - https://aerokube.com/selenoid/latest/#_video_recording)

g)	After performing c and e steps you can see like this 

h)	 

i)	Selenoid by default will allow 5 parallel sessions. If you want to have more please override the –limit parameter like,

j)	docker run -d --name selenoid -p 4444:4444 -v //var/run/docker.sock:/var/run/docker.sock -v ${current}/config/:/etc/selenoid/:ro -v /c/Users/asakthiv/selenoid/video/:/opt/selenoid/video/ -e OVERRIDE_VIDEO_OUTPUT_DIR=/c/Users/asakthiv/selenoid/video/ aerokube/selenoid:latest-release –limit 10 (Instead of step c)


4.	Creating tests:
a)I have created a sample test case in java-selenium 
 

If you are not sure about the capabilities to be passed, click the capabilities in the localhost:8090 window in the top right corner and choose the language, 
Dropdown will list all the values mentioned in the dropdown. You can choose any one value from dropdown to view exact capability.
 







5.	Viewing Live preview:

We can view the live preview by clicking on the session id. After running your test look for the session id in the preview window.
 
 



6.	Accessing the videos:
Videos can be accessed after the test execution with http://localhost:4444/video/ 
Since I have given specific capability videoName , it has used that for naming the video. Otherwise the session id will be the video name.

We can also directly access the video with url like http://localhost:4444/video/+filename
 

Videos will be automatically stored in the mounted folder in the host. In my case it is like this, 
7.	Deleting the videos:
https://aerokube.com/selenoid/latest/#_deleting_video_files

or

manually delete in the local host where the videos are stored.



Other Useful commands or links:
For more specific capabilities – Please refer https://aerokube.com/selenoid/latest/#_special_capabilities
docker logs selenoid 🡪 to view the selenoid logs 
docker logs –follow selenoid 🡪 to follow the logs (While spinning the container you can remove –d to follow the logs directly)
Timeouts modification - https://aerokube.com/selenoid/latest/#_limits_and_timeouts
Docker Images tags selenoid -  https://aerokube.com/selenoid/latest/#_browser_image_information


















 


