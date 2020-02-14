#!/bin/bash

# example command:
# sh scripts/get-secrets.sh

set -e

# Set the following
spName=jdk8s
resourceGroup=jdk8s
subName="ca-jessde-demo-test"
acrName=jdk8s

# set the subscription
az account set --subscription "$subName" && echo "Your default subscription has been set to: $subName"

# Create a service principal
    echo "Creating service principal..."
    spInfo=$(az ad sp create-for-rbac --name "$spName" \
            --role contributor \
            --sdk-auth)

    if [ $? == 0 ]; then
        # get acr name
        echo "Retrieving Container Registry info..."
        acrName=$(az acr list -g $resourceGroup -o tsv --query "[0].name")

        echo '========================================================='
        echo 'GitHub secrets for configuring GitHub workflow'
        echo '========================================================='
        echo "AZURE_CREDENTIALS: $spInfo"
        echo "CONTAINER_REGISTRY: $(az acr list -g $resourceGroup -o tsv --query [0].loginServer)"
        echo "REGISTRY_USERNAME: $(az acr credential show -n $acrName --query username -o tsv)"
        echo "REGISTRY_PASSWORD: $(az acr credential show -n $acrName -o tsv --query passwords[0].value)"
        echo '========================================================='
    else
        "An error occurred. Please try again."
         exit 1
    fi
