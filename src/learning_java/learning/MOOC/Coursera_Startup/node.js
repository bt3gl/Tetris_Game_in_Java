#!/usr/bin/env node

var fs = require('fs')
var outfile = "hello.txt"
var out = "Hello Worldddddd!!!!!\n"
fs.writeFileSync(outfile, out)
console.log("Script: " + __filename + "\nwrote " + out + "to: " + outfile)
