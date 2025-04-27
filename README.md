# IP-Addr-Counter

## Task

https://github.com/Ecwid/new-job/blob/master/IP-Addr-Counter.md

You have a simple text file with IPv4 addresses. One line is one address, line by line:

```
145.67.23.4
8.34.5.23
89.54.3.124
89.54.3.124
3.45.71.5
...
```

The file is unlimited in size and can occupy tens and hundreds of gigabytes.

You should calculate the number of __unique addresses__ in this file using as little memory and time as possible.
There is a "naive" algorithm for solving this problem (read line by line, put lines into HashSet).
It's better if your implementation is more complicated and faster than this naive algorithm.

Some details:
- You can only use the features of the standard Java/Kotlin library.
- You should write in Java (version 21 and above) or Kotlin.
- The assignment must have a working main() method, to demonstrate how it works
- The completed assignment should be posted on GitHub
---

## Decision

Have 2 versions (v1-quick, v2 - for large files).
Can print progress while count.

You can run with parameters
```
 -v 2 -f test/test_data1 -r 1000 -p
```
* "-v X" - version of use version. By default "v2"
* "-f file_path" - path to processing file. By default "ip_addresses"
* "-r X" - count of record for print processing message. By default "1 000 000"
* "-p" - flag for print processing messages. by default "FALSE"