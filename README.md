# NoobStom

NoobStom is a template for a NoobCloud compatible Minestom server. It only contains basic features that allow NoobCloud
to start it.

## Config

NoobStom automatically generates a config file on startup, it has the following content:
```json
{
  "velocityEnabled": true,
  "velocitySecret": "Your forwarding secret"
}
```
Please provide the forwarding-secret from Velocity because otherwise you won't be able to connect to the server.