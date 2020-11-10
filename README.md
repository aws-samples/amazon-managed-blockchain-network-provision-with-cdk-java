# Provision Amazon Managed Blockchain Network using CDK and Java Programming Language

## Introduction

This project provides sample Java code to provision Amazon Managed Blockchain Network using AWS Cloud Development Kit(CDK).

Know more details about AWS CDK here https://aws.amazon.com/cdk/

Know more details about Amazon Managed Blockchain here https://aws.amazon.com/managed-blockchain

## Prerequisites

* Java 8 (v1.8) or later
* Apache Maven 3.5 or later
* CDK 1.69 
* AWS Command Line Interface V2

## How to use this Project

To provision Amazon Blockchain Network using this sample following details have to be provided in AmbNetworkStack.java file.

* NETWORK_NAME
* NETWORK_DESCRIPTION
* FRAMEWORK_TYPE
* FRAMEWORK_VERSION
* EDITION
* THRESHOLD_PERCENTAGE
* THRESHOLD_COMPARATOR
* PROPOSAL_DURATION
* MEMBER_NAME
* MEMBER_DESCRIPTION
* ADMIN_USERNAME
* ADMIN_PASSWORD

For description of above fields and allowed values refer https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/AWS_ManagedBlockchain.html 

After providing above values, execute the following commands.

* `cdk synth` this command generates the CloudFormation template under cdk.out folder. Validate the template.
* `cdk deploy` this command deploys CloudFormation stack

