SHELL 	       := /bin/bash

start:
	lein cljsbuild auto &
	lein ring server-headless

lint:
	lein eastwood
	lein kibit
	lein bikeshed

clean:
	lein clean

help:
	make -rpn | sed -n -e '/^$$/ { n ; /^[^ ]*:/p; }' | sort | egrep --color '^[^ ]*:'
.PHONY:
	help start lint
