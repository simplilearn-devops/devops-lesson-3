FROM centos:latest
RUN yum install -y wget
RUN yum install -y zip
RUN cd /tmp && wget -q --no-check-certificate --no-cookies \
   --header "Cookie: oraclelicense=accept-securebackup-cookie" \ 
http://download.oracle.com/otn-pub/java/jdk/8u161-b12/2f38c3b165be4555a1fa6e98c45e0808/jdk-8u161-linux-x64.rpm
RUN rpm -i /tmp/jdk*linux-x64.rpm
ENV JAVA_HOME=/usr/java/latest
RUN cd /tmp && wget https://sourceforge.net/projects/j-trac/files/jtrac/2.1.0/jtrac-2.1.0.zip
RUN yum install -y unzip
RUN cd /opt && unzip /tmp/jtrac*.zip
RUN cd /opt/jtrac && mv start.bat start.sh && chmod +x start.sh
RUN rm /tmp/jdk*linux-x64.rpm /tmp/jtrac*.zip
WORKDIR /opt/jtrac
CMD ./start.sh

