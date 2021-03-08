# Copyright (c) 2021 Wladislaw Wagner (www.trustincode.de), (vitagroup AG).
#
# This file is part of Project EHRbase
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.


import os
import base64
from requests import request



def get_variables(sut="LOCAL", auth_type="BASIC"):

    #////////////////////////////////////////////////
    #// CENTRAL RESEARCH REPOSITORY              ///
    #//////////////////////////////////////////////

    # get urls and credentials of remote environments from ENVs
    if sut != "LOCAL":
        FHIRBRIDGE_URL = os.getenv('FHIRBRIDGE_URL')
        EHRBASE_URL = os.getenv('EHRBASE_URL')
        EHRBASE_USER = os.getenv('EHRBASE_USER')
        EHRBASE_PASS = os.getenv('EHRBASE_PASS')

        auth = str(base64.b64encode(f"{EHRBASE_USER}:{EHRBASE_PASS}".encode("utf-8")), "utf-8")

    # LOCAL CONFIG W/ BASIC AUTH
    if sut == "LOCAL":
        # local environment: is used per default
        # the same is also used for continuous integration on CircleCI
        LOCAL_CONFIG = {
            "SUT": "LOCAL",
            "BASE_URL": "http://localhost:8888/fhir-bridge/fhir",
            "EHRBASE_URL": "http://localhost:8080/ehrbase/rest/openehr/v1",
            "HEARTBEAT_URL": "http://localhost:8080/ehrbase/",
            "CREDENTIALS": ["myuser", "myPassword432"],
            "SECURITY_AUTHTYPE": "BASIC",
            "AUTHORIZATION": {
                "Authorization": "Basic " + str(base64.b64encode("myuser:myPassword432".encode("utf-8")), "utf-8")
            },
            "NODENAME": "local.execution.org",
            "CONTROL_MODE": "robot"
        }
        return LOCAL_CONFIG
    
    # DEV (GWDG) CONFIG W/ BASIC AUTH
    if sut == "DEV":
        DEV_CONFIG = {
            "SUT": "DEV",
            "BASE_URL": FHIRBRIDGE_URL + '/fhir',
            "EHRBASE_URL": EHRBASE_URL + '/ehrbase/rest/openehr/v1',
            "HEARTBEAT_FHIRBRIDGE": FHIRBRIDGE_URL,
            "HEARTBEAT_EHRBASE": EHRBASE_URL,
            "CREDENTIALS": [EHRBASE_USER, EHRBASE_PASS],
            "SECURITY_AUTHTYPE": "BASIC",
            "AUTHORIZATION": {
                "Authorization": f"Basic {auth}"
            },
            "NODENAME": "crr_dev.execution.org",
            "CONTROL_MODE": "robot"
        }
        return DEV_CONFIG
    
    # STAGING (GWDG) CONFIG W/ BASIC AUTH
    if sut == "STAGING":
        STAGING_CONFIG = {
            "SUT": "STAGING",
            "BASE_URL": FHIRBRIDGE_URL + '/fhir',
            "EHRBASE_URL": EHRBASE_URL + '/ehrbase/rest/openehr/v1',
            "HEARTBEAT_FHIRBRIDGE": FHIRBRIDGE_URL,
            "HEARTBEAT_EHRBASE": EHRBASE_URL,
            "CREDENTIALS": [EHRBASE_USER, EHRBASE_PASS],
            "SECURITY_AUTHTYPE": "BASIC",
            "AUTHORIZATION": {
                "Authorization": f"Basic {auth}"
            },
            "NODENAME": "crr_staging.execution.org",
            "CONTROL_MODE": "robot"
        }
        return STAGING_CONFIG
    
    # PREPROD (GWDG) CONFIG W/ BASIC AUTH
    if sut == "PREPROD":
        PREPROD_CONFIG = {
            "SUT": "PREPROD",
            "BASE_URL": FHIRBRIDGE_URL + '/fhir',
            "EHRBASE_URL": EHRBASE_URL + '/ehrbase/rest/openehr/v1',
            "HEARTBEAT_FHIRBRIDGE": FHIRBRIDGE_URL,
            "HEARTBEAT_EHRBASE": EHRBASE_URL,
            "CREDENTIALS": [EHRBASE_USER, EHRBASE_PASS],
            "SECURITY_AUTHTYPE": "BASIC",
            "AUTHORIZATION": {
                "Authorization": f"Basic {auth}"
            },
            "NODENAME": "crr_preprod.execution.org",
            "CONTROL_MODE": "robot"
        }
        return PREPROD_CONFIG

    #/////////////////////////////////////////////////////
    #// CENTRAL TRANSACTIONAL REPOSITORY              ///
    #///////////////////////////////////////////////////

    # TODO: Add configs for 'transactional repo' environments
