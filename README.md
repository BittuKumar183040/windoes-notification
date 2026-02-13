### Notification Service

API Call:
POST : <url>/notification-manager/email

Body: 
```json
{
    "to": ["email1", "email2"],
    "cc": ["email3"],
    "event": "new_registration",
    "information": {
        "name": "Bittu Kumar",
        "tenant": "TCS"
    }
}
```
