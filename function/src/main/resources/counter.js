'use strict';

var counter = 0;
exports.initializer = function(ctx, callback) {
    ++counter;
    callback(null, "");
};

exports.handler = function(event, context, callback) {
    console.log('counter is %d', counter);
    callback(null, String(counter));
};