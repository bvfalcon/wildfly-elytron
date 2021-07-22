The difference with original wildfly-elytron libraries is that console tool **add-user**.[bat|sh] use now encrypting algorithm **SHA-256** instead of obsolete and unreliable **MD5**.

For this reason in SASL must now use
```
<mechanism mechanism-name="DIGEST-SHA-256">
```

instead of
```
<mechanism mechanism-name="DIGEST-MD5">
```

Also SHA-256 used in **property-realm**. 
