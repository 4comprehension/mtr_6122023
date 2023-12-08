FROM golang:alpine AS builder
RUN apk update && apk add --no-cache git
WORKDIR $GOPATH/src/mypackage/myapp/
COPY . .
RUN go get -d -v
RUN go build -o /go/bin/message_relay
FROM scratch
COPY --from=builder /go/bin/message_relay /go/bin/message_relay
ENTRYPOINT ["/go/bin/message_relay"]
