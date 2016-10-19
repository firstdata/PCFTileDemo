# PCFTileDemo
![alt text](https://d1fto35gcfffzn.cloudfront.net/images/products/pivotal-cloud-foundry/logo-pivotal-cloud-foundry.svg "PCF")


This is a [Spring Boot](https://projects.spring.io/spring-boot/) web app that demonstrates how to build an eCommerce web site that can accept payments via the [First Data](https://firstdata.com) Tile on the [Pivotal Cloud Foundry PaaS](https://pivotal.io/platform)

### Pre-requisites

1. Get your free PCF account by stopping by the First Data table on the Money 20/20 Hackathon floor.

2. Login to the PCF console from a Browser at https://console.run.pivotal.io

3. Click the Marketplace link and then the 'First Data API' Tile.

4. Select the 'First Data Anonymous Plan' and then tap the 'Select Plan' button.  

5. Provide an instance name (e.g. myteamname_FD_service) and click 'Add'.

  (You have now created a PCF 'connection' to a Sandbox merchant and developer account on the First Data API)

6. Install the PCF command-line tools as per [these instructions](https://docs.cloudfoundry.org/cf-cli/install-go-cli.html)

### Coding

1. Edit manifest.yml: replace the #service name with the instance name you specified in Step 5 above.

2. Build the app from the command line with:
```shell
$ mvn clean install
```

### Deploy and run

1. Connect to PCF from the command-line with:
```shell
$ cf login -a https://api.run.pivotal.io
```
Supply the PCF credentials, organization and space names that you obtained when you signed up for your PCF account.

2. Deploy your app with:
```shell
cf push fdtiledemo
```
Your app will be deployed and launched

3. Test your app by pointing your Browser at the url specified in the output from cf push (e.g. https://fdtiledemo.cfapps.io)

  Supply the following values to make a test payment:

| Field Name  | Value  |
| -------- |---------|
|Name|{any string}|
|Amount (in pennies)|{a whole number, eg 1299}|
|Currency|USD|
|Credit Card Number|4788250000028291|
|Expiry Date|{any MMYY in the future, eg 1218}|
|CVV|{any 3 digits}|
|Card Type|Visa|
|Transaction Type|Authorize|

  Click the Submit button.  The JSON response will appear at the top of the page
  
