FROM amazoncorretto:11
RUN yum update -y &&\
	yum install -y gcc gcc-c++ autoconf automake make pkgconfig libtool &&\
	yum install -y zlib-devel libpng-devel libjpeg-turbo-devel libtiff-devel libwebp-devel openjpeg2-devel giflib-devel &&\
	yum install -y gzip tar &&\
    yum clean all &&\
    rm -rf /var/cache/yum

WORKDIR /opt

COPY leptonica-1.82.0.tar.gz .
RUN gunzip -v leptonica-1.82.0.tar.gz
RUN tar xvf leptonica-1.82.0.tar

WORKDIR /opt/leptonica-1.82.0

ENV LD_LIBRARY_PATH $LD_LIBRARY_PATH:/usr/local/lib

RUN ./autogen.sh
RUN ./configure
RUN make && make install
ENV PKG_CONFIG_PATH $PKG_CONFIG_PATH:/usr/local/lib/pkgconfig

WORKDIR /opt

COPY tesseract-5.2.0.tar.gz .
RUN gunzip -v tesseract-5.2.0.tar.gz
RUN tar xvf tesseract-5.2.0.tar

WORKDIR /opt/tesseract-5.2.0

RUN ./autogen.sh
RUN ./configure
RUN make && make install

ARG TESSDATA_PREFIX=/usr/local/share/tessdata
ENV TESSDATA_PREFIX ${TESSDATA_PREFIX}

WORKDIR /opt

RUN mkdir assets
RUN mkdir outputs

COPY ./tessdata/eng.traineddata ${TESSDATA_PREFIX}