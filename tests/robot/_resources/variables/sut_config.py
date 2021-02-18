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

# ENVIRONMENT VARS
DEV_FHIRBRIDGE_URL = os.getenv('DEV_FHIRBRIDGE_URL')
DEV_EHRBASE_URL = os.getenv('DEV_EHRBASE_URL')
DEV_EHRBASE_USER = os.getenv('DEV_EHRBASE_USER')
DEV_EHRBASE_PASS = os.getenv('DEV_EHRBASE_PASS')

X = f"{DEV_EHRBASE_USER}:{DEV_EHRBASE_PASS}"
Xb64 = base64.b64encode(X.encode("utf-8"))
XS = str(Xb64, "utf-8")
DEV_AUTH = f"Basic {XS}"

STAGING_FHIRBRIDGE_URL = os.getenv('STAGING_FHIRBRIDGE_URL')
STAGING_EHRBASE_URL = os.getenv('STAGING_EHRBASE_URL')
STAGING_EHRBASE_USER = os.getenv('STAGING_EHRBASE_USER')
STAGING_EHRBASE_PASS = os.getenv('STAGING_EHRBASE_PASS')

Y = f"{STAGING_EHRBASE_USER}:{STAGING_EHRBASE_PASS}"
Yb64 = base64.b64encode(X.encode("utf-8"))
YS = str(Yb64, "utf-8")
STAGING_AUTH = f"Basic {YS}"




# SUT CONFIGURATIONS
"""
CONFIG              SUT STARTUP AUTOMATED?      COMMENT
------              ----------------------      -------

CI == LOCAL
LOCAL
DEV (GWDG)            
TEST (GWDG)
"""

# local environment: for local execution
# the same is used for continuous integration on CircleCI
LOCAL_CONFIG = {
    "SUT": "LOCAL",
    "BASE_URL": "http://localhost:8888/fhir-bridge/fhir",
    "EHRBASE_URL": "http://localhost:8080/ehrbase/rest/openehr/v1",
    "HEARTBEAT_URL": "http://localhost:8080/ehrbase/",
    "CREDENTIALS": ["ehrbase-user", "SuperSecretPassword"],
    "SECURITY_AUTHTYPE": "BASIC",
    "AUTHORIZATION": {
        "Authorization": "Basic ZWhyYmFzZS11c2VyOlN1cGVyU2VjcmV0UGFzc3dvcmQ="
    },
    "NODENAME": "local.execution.org",
    "CONTROL_MODE": "robot"
}

DEV_CONFIG = {
    "SUT": "DEV",
    "BASE_URL": DEV_FHIRBRIDGE_URL + '/fhir',
    "EHRBASE_URL": DEV_EHRBASE_URL + '/ehrbase/rest/openehr/v1',
    "HEARTBEAT_FHIRBRIDGE": DEV_FHIRBRIDGE_URL,
    "HEARTBEAT_EHRBASE": DEV_EHRBASE_URL,
    "CREDENTIALS": [DEV_EHRBASE_USER, DEV_EHRBASE_PASS],
    "SECURITY_AUTHTYPE": "BASIC",
    "AUTHORIZATION": {
        "Authorization": DEV_AUTH
    },
    "NODENAME": "crr_dev.execution.org",
    "CONTROL_MODE": "robot"
}

STAGING_CONFIG = {
    "SUT": "STAGING",
    "BASE_URL": STAGING_FHIRBRIDGE_URL + '/fhir',
    "EHRBASE_URL": STAGING_EHRBASE_URL + '/ehrbase/rest/openehr/v1',
    "HEARTBEAT_FHIRBRIDGE": STAGING_FHIRBRIDGE_URL,
    "HEARTBEAT_EHRBASE": STAGING_EHRBASE_URL,
    "CREDENTIALS": [STAGING_EHRBASE_USER, STAGING_EHRBASE_PASS],
    "SECURITY_AUTHTYPE": "BASIC",
    "AUTHORIZATION": {
        "Authorization": STAGING_AUTH
    },
    "NODENAME": "crr_staging.execution.org",
    "CONTROL_MODE": "robot"
}


def get_variables(sut="LOCAL", auth_type="BASIC", nodocker="NEIN!"):
    # LOCAL CONFIG W/ BASIC AUTH
    if sut == "LOCAL":
        return LOCAL_CONFIG
    
    # DEV (GWDG) CONFIG W/ BASIC AUTH
    if sut == "DEV":
        return DEV_CONFIG
    
    # STAGING (GWDG) CONFIG W/ BASIC AUTH
    if sut == "STAGING":
        return STAGING_CONFIG
