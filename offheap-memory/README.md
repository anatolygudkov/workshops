## Useful commands

In the following assembly we can see that C2 compiles Unsafe.putX to one single ```mov``` even with no boundary checking.
```
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:+PrintInlining org.ag.workshop.offheap.OffHeapDatabaseTest &> OffHeapDatabaseTest.asm
```

C2 uses SIMD to compare arrays (in usernameEqualsTo). Compare to OffHeapDatabase's version which is not so effective, since Unsafe doesn't provide a way to compare two memory areas with an intrinsic. But because of no boundary checking + good cache locality, the off-heap version isn't much worse than heap version.
```
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:+PrintInlining org.ag.workshop.offheap.HeapDatabaseTest &> HeapDatabaseTest.asm
```

Check memory allocated by processes (RSS - resident memory, VSZ - virtual memory available)
```
ps -eo pid,rss,vsz,args | sort -k 2 -r | grep -E 'RSS|DatabaseTest'
```
