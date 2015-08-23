SHELL 	       := /bin/bash

start:
	lein cljsbuild once
	lein ring server-headless

auto-compile:
	lein cljsbuild auto

lint:
	lein eastwood
	lein kibit
	lein bikeshed

clean:
	lein clean

help:
	@ echo "[HELP]: Run auto-compile for cljs->js & make[ start] to start server"
	@ make -rpn | sed -n -e '/^$$/ { n ; /^[^ ]*:/p; }' | sort | egrep --color '^[^ ]*:'

.PHONY:
	help start lint
