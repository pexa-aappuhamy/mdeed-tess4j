# Although tesseract does not need node or yarn
# the node 19 alpine image supports Apple M1 and Intel

FROM node:19-alpine3.15

RUN apk add openjdk11
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk

RUN apk add tesseract-ocr
ENV TESSDATA_PREFIX /usr/share/tessdata
