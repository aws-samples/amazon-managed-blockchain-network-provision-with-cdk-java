/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: MIT-0
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;

import software.amazon.awscdk.services.managedblockchain.CfnMember;
import software.amazon.awscdk.services.managedblockchain.CfnNode;
import software.amazon.awscdk.services.managedblockchain.CfnMember.ApprovalThresholdPolicyProperty;
import software.amazon.awscdk.services.managedblockchain.CfnMember.MemberConfigurationProperty;
import software.amazon.awscdk.services.managedblockchain.CfnMember.MemberFabricConfigurationProperty;
import software.amazon.awscdk.services.managedblockchain.CfnMember.MemberFrameworkConfigurationProperty;
import software.amazon.awscdk.services.managedblockchain.CfnMember.NetworkConfigurationProperty;
import software.amazon.awscdk.services.managedblockchain.CfnMember.NetworkFabricConfigurationProperty;
import software.amazon.awscdk.services.managedblockchain.CfnMember.NetworkFrameworkConfigurationProperty;
import software.amazon.awscdk.services.managedblockchain.CfnMember.VotingPolicyProperty;
import software.amazon.awscdk.services.managedblockchain.CfnNode.NodeConfigurationProperty;

public class AmbNetworkStack extends Stack {
    public AmbNetworkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public AmbNetworkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final String networkName = "<NETWORK_NAME>";
        final String networkDescription = "<NETWORK_DESCRIPTION>";
        final String framework = "<FRAMEWORK_TYPE>";//Allowed value HYPERLEDGER_FABRIC
        final String frameworkVersion = "<FRAMEWORK_VERSION>";
        final String edition = "<EDITION>"; //Allowed values are STARTER or STANDARD
        final long thresholdPercentage = 50; //<THRESHOLD_PERCENTAGE> Number between 0 and 100
        final String thresholdComparator = "GREATER_THAN"; //<THRESHOLD_COMPARATOR> Allowed values are GREATER_THAN or GREATER_THAN_OR_EQUAL_TO
        final long proposalDurationInHours = 24; //<PROPOSAL_DURATION> Number between 1 and 168

        final String memberName = "<MEMBER_NAME>";
        final String memberDescription = "<MEMBER_DESCRIPTION>";
        final String memberAdminUsername = "<ADMIN_USERNAME>";
        final String memberAdminPassword = "<ADMIN_PASSWORD>!";
        
        final String peerNodeAvailabilityZone = "<AVAILABILITY_ZONE>"; //Availability Zone e.g. us-east-1a
        final String peerNodeInstanceType = "<INSTANCE_TYPE>"; //Instance Type bc.t3.small


        // The code that defines your stack goes here
        NetworkConfigurationProperty networkConfiguration = NetworkConfigurationProperty.builder()
                .name(networkName).description(networkDescription).frameworkVersion(frameworkVersion)
                .framework(framework)
                .networkFrameworkConfiguration(NetworkFrameworkConfigurationProperty.builder()
                        .networkFabricConfiguration(
                                NetworkFabricConfigurationProperty.builder().edition(edition).build())
                        .build())
                .votingPolicy(VotingPolicyProperty.builder()
                        .approvalThresholdPolicy(ApprovalThresholdPolicyProperty.builder().proposalDurationInHours(proposalDurationInHours)
                                .thresholdComparator(thresholdComparator).thresholdPercentage(thresholdPercentage).build())
                        .build())
                .build();
        MemberConfigurationProperty memberConfiguration = MemberConfigurationProperty.builder().name(memberName)
                .description(memberDescription)
                .memberFrameworkConfiguration(MemberFrameworkConfigurationProperty.builder()
                        .memberFabricConfiguration(MemberFabricConfigurationProperty.builder()
                                .adminUsername(memberAdminUsername).adminPassword(memberAdminPassword).build())
                        .build())
                .build();
        CfnMember ambMeber = CfnMember.Builder.create(this, "MyFirstAMBNetwork")
                .networkConfiguration(networkConfiguration).memberConfiguration(memberConfiguration).build();

        NodeConfigurationProperty nodeConfiguration = NodeConfigurationProperty.builder().availabilityZone(peerNodeAvailabilityZone)
                .instanceType(peerNodeInstanceType).build();

        CfnNode ambMemberNode = CfnNode.Builder.create(this, "FirstAMBMemberNode").networkId(ambMeber.getAttrNetworkId())
                .memberId(ambMeber.getAttrMemberId()).nodeConfiguration(nodeConfiguration).build();
    }
}
