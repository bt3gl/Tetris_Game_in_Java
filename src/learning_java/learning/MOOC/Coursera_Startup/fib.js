#!/usr/bin/env node

function fib(n){
    return n<2?n:fib(n-1)+fib(n-2);
}


for(i=0; i<=20; i++){
   var out =  fib(i);
   console.log(out + ', ');
}
