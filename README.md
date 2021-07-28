Differences with original wildfly-elytron
------

1. Console tool **add-user**.[bat|sh] use now encrypting algorithm **SHA-256** instead of obsolete and unreliable **MD5**.

For this reason in SASL must now use
```
<mechanism mechanism-name="DIGEST-SHA-256">
```

instead of
```
<mechanism mechanism-name="DIGEST-MD5">
```

Also SHA-256 used in **property-realm**. 

2. Bugfix (ClassNotFoundException) and improvement (parameter "layers" added) of **elytron-tool**.[bat|sh]
