##Useful commands

```
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:+PrintInlining org.ag.workshop.offheap.OffHeapDatabase &> OffHeapDatabase.asm
```


ps -eo pid,rss,vsz,args | sort -k 2 -r | grep -E 'RSS|HeapDatabase'

