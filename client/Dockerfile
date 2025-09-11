FROM alpine/curl:latest
WORKDIR /client
COPY test_cases.sh .
RUN chmod +x test_cases.sh
ENTRYPOINT ["/client/test_cases.sh"]