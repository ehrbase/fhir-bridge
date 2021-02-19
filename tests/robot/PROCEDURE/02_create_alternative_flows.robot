# Copyright (c) 2020 Peter Wohlfarth (Appsfactory GmbH), Wladislaw Wagner (Vitasystems GmbH),
# Dave Petzold (Appsfactory GmbH) & Pauline Schulz (Appsfactory GmbH)
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



*** Settings ***
Resource                ${EXECDIR}/robot/_resources/suite_settings.robot

Test Setup              generic.prepare new request session    Prefer=return=representation
...															   Authorization=${AUTHORIZATION['Authorization']}

Force Tags              procedure_create    create



*** Variables ***




*** Test Cases ***

001 Create Radiology Procedures - Chest X-Ray Alternative dcm datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_dcm_datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented    alternative    dcm    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray dcm datetime    create-radiology-procedures-chest-x-ray_dcm_datetime.json
    procedure.validate response - 201


002 Create Radiology Procedures - Chest X-Ray Alternative dcm period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_dcm_period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented   alternative    dcm    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray dcm period    create-radiology-procedures-chest-x-ray_dcm_period.json
    procedure.validate response - 201



003 Create Radiology Procedures - Chest X-Ray Alternative dcm period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_dcm_period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented   alternative    dcm    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray dcm period_2   create-radiology-procedures-chest-x-ray_dcm_period_2.json
    procedure.validate response - 201



004 Create Radiology Procedures - Chest X-Ray Alternative sct datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_sct_datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented   alternative    sct    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray sct datetime    create-radiology-procedures-chest-x-ray_sct_datetime.json
    procedure.validate response - 201



005 Create Radiology Procedures - Chest X-Ray sct period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_sct_period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented   alternative    sct    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray sct period    create-radiology-procedures-chest-x-ray_sct_period.json
    procedure.validate response - 201



006 Create Radiology Procedures - Chest X-Ray sct period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray_sct_period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-ready_not-implemented   alternative    sct    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray sct period_2    create-radiology-procedures-chest-x-ray_sct_period_2.json
    procedure.validate response - 201


007 Create Respiratory Therapies - Artificial Respiration Alternative sct datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-artificial-respiration_sct-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	respiratory-therapies    artificial-respiration    valid    not-ready    not-ready_not-implemented    alternative    sct    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Respiratory Therapies - Artificial Respiration sct datetime     create-respiratory-therapies-artificial-respiration_sct-datetime.json
    procedure.validate response - 201


008 Create Respiratory Therapies - Artificial Respiration Alternative sct period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-artificial-respiration_sct-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	respiratory-therapies    artificial-respiration    valid    not-ready    not-ready_not-implemented    alternative    sct    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Respiratory Therapies - Artificial Respiration sct period    create-respiratory-therapies-artificial-respiration_sct-period.json
    procedure.validate response - 201



009 Create Respiratory Therapies - Artificial Respiration Alternative sct period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-artificial-respiration_sct-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	respiratory-therapies    artificial-respiration    valid    not-ready    not-ready_not-implemented    alternative    sct    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Respiratory Therapies - Artificial Respiration sct period_2    create-respiratory-therapies-artificial-respiration_sct-period_2.json
    procedure.validate response - 201



010 Create Respiratory Therapies - Artificial Respiration Alternative ops datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-artificial-respiration_ops-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	respiratory-therapies    artificial-respiration    valid    not-ready    not-ready_not-implemented    alternative    ops    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Respiratory Therapies - Artificial Respiration ops datetime    create-respiratory-therapies-artificial-respiration_ops-datetime.json
    procedure.validate response - 201



011 Create Respiratory Therapies - Artificial Respiration Alternative ops period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-artificial-respiration_ops-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	respiratory-therapies    artificial-respiration    valid    not-ready    not-ready_not-implemented    alternative    ops    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Respiratory Therapies - Artificial Respiration ops period    create-respiratory-therapies-artificial-respiration_ops-period.json
    procedure.validate response - 201



012 Create Respiratory Therapies - Artificial Respiration Alternative ops period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-artificial-respiration_ops-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	respiratory-therapies    artificial-respiration    valid    not-ready    not-ready_not-implemented    alternative    ops    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Respiratory Therapies - Artificial Respiration ops period_2    create-respiratory-therapies-artificial-respiration_ops-period_2.json
    procedure.validate response - 201



013 Create Generic Therapy - Dialysis sct datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-sct-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-ready_not-implemented    alternative    sct    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis  Generic Therapy - Dialysis sct datetime  create-dialysis-sct-datetime.json
    procedure.validate response - 201


014 Create Generic Therapy - Dialysis sct period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-sct-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-ready_not-implemented    alternative    sct    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis  Generic Therapy - Dialysis sct period  create-dialysis-sct-period.json
    procedure.validate response - 201



015 Create Generic Therapy - Dialysis sct period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-sct-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-ready_not-implemented    alternative    sct    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis  Generic Therapy - Dialysis sct period_2  create-dialysis-sct-period_2.json
    procedure.validate response - 201



016 Create Generic Therapy - Dialysis ops datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-ops-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-ready_not-implemented    alternative    ops    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis  Generiy Therapy - Dialysis ops datetime  create-dialysis-ops-datetime.json
    procedure.validate response - 201



017 Create Generic Therapy - Dialysis ops period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-ops-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-ready_not-implemented    alternative    ops    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis  Generic Therapy - Dialysis ops period  create-dialysis-ops-period.json
    procedure.validate response - 201



018 Create Generic Therapy - Dialysis ops period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-ops-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-ready_not-implemented    alternative    ops    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis  Generic Therapy - Dialysis ops period_2  create-dialysis-ops-period_2.json
    procedure.validate response - 201



019 Create Generic Therapy - Extracorporeal Membrane Oxygenation sct datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-extracorporeal-membrane-oxygenation-in-progress_sct_datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    extracorporeal    membrane     oxygenation    valid    not-ready    not-ready_not-implemented    alternative    sct    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create extracorporeal membrane oxygenation  Generic Therapy - Extracorporeal Membrane Oxygenation sct datetime  create-extracorporeal-membrane-oxygenation-in-progress_sct_datetime.json
    procedure.validate response - 201


020 Create Generic Therapy - Extracorporeal Membrane Oxygenation sct period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-create-extracorporeal-membrane-oxygenation-in-progress-sct_period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    extracorporeal    membrane     oxygenation    valid    not-ready    not-ready_not-implemented    alternative    sct    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create extracorporeal membrane oxygenation  Generic Therapy - Extracorporeal Membrane Oxygenation sct datetime  create-extracorporeal-membrane-oxygenation-in-progress_sct_period.json
    procedure.validate response - 201



021 Create Generic Therapy - Extracorporeal Membrane Oxygenation sct period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-extracorporeal-membrane-oxygenation-in-progress_sct_period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    extracorporeal    membrane     oxygenation    valid    not-ready    not-ready_not-implemented    alternative    sct    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create extracorporeal membrane oxygenation  Generic Therapy - Extracorporeal Membrane Oxygenation sct datetime  create-extracorporeal-membrane-oxygenation-in-progress_sct_period_2.json
    procedure.validate response - 201



022 Create Generic Therapy - Extracorporeal Membrane Oxygenation ops datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-extracorporeal-membrane-oxygenation-in-progress_ops_datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    extracorporeal    membrane     oxygenation    valid    not-ready    not-ready_not-implemented    alternative    ops    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create extracorporeal membrane oxygenation  Generic Therapy - Extracorporeal Membrane Oxygenation sct datetime  create-extracorporeal-membrane-oxygenation-in-progress_ops_datetime.json
    procedure.validate response - 201



023 Create Generic Therapy - Extracorporeal Membrane Oxygenation ops period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-extracorporeal-membrane-oxygenation-in-progress_ops_period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    extracorporeal    membrane     oxygenation    valid    not-ready    not-ready_not-implemented    alternative    ops    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create extracorporeal membrane oxygenation  Generic Therapy - Extracorporeal Membrane Oxygenation sct datetime  create-extracorporeal-membrane-oxygenation-in-progress_ops_period.json
    procedure.validate response - 201



024 Create Generic Therapy - Extracorporeal Membrane Oxygenation ops period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-extracorporeal-membrane-oxygenation-in-progress_ops_period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    extracorporeal    membrane     oxygenation    valid    not-ready    not-ready_not-implemented    alternative    ops    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create extracorporeal membrane oxygenation  Generic Therapy - Extracorporeal Membrane Oxygenation sct datetime  create-extracorporeal-membrane-oxygenation-in-progress_ops_period_2.json
    procedure.validate response - 201


025 Create Generic Therapy - Apheresis sct datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-apheresis-sct-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    apheresis    valid    not-ready    not-ready_not-implemented    alternative    sct    datetime

    ehr.create new ehr    000_ehr_status.json
	procedure.create apheresis  Generic Therapy - Apheresis sct datetime  create-apheresis-sct-datetime.json
	procedure.validate response - 201


026 Create Generic Therapy - Apheresis sct period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-apheresis-sct-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    apheresis    valid    not-ready    not-ready_not-implemented    alternative    sct    period

    ehr.create new ehr    000_ehr_status.json
	procedure.create apheresis  Generic Therapy - Apheresis sct period  create-apheresis-sct-period.json
	procedure.validate response - 201
	


027 Create Generic Therapy - Apheresis sct period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-apheresis-sct-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    apheresis    valid    not-ready    not-ready_not-implemented    alternative    sct    period_2

    ehr.create new ehr    000_ehr_status.json
	procedure.create apheresis  Generic Therapy - Apheresis sct period_2  create-apheresis-sct-period_2.json
	procedure.validate response - 201



028 Create Generic Therapy - Apheresis ops datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-apheresis-ops-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    apheresis    valid    not-ready    not-ready_not-implemented    alternative    ops    datetime

    ehr.create new ehr    000_ehr_status.json
	procedure.create apheresis  Generic Therapy - Apheresis ops datetime  create-apheresis-ops-datetime.json
	procedure.validate response - 201



029 Create Generic Therapy - Apheresis ops period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-apheresis-ops-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    apheresis    valid    not-ready    not-ready_not-implemented    alternative    ops    period

    ehr.create new ehr    000_ehr_status.json
	procedure.create apheresis  Generic Therapy - Apheresis ops period  create-apheresis-ops-period.json
	procedure.validate response - 201



030 Create Generic Therapy - Apheresis ops period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-apheresis-ops-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    apheresis    valid    not-ready    not-ready_not-implemented    alternative    ops    period_2

    ehr.create new ehr    000_ehr_status.json
	procedure.create apheresis  Generic Therapy - Apheresis ops period_2  create-apheresis-ops-period_2.json
	procedure.validate response - 201



031 Create Generic Therapy - Prone Position sct datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-prone-position-in-progress_sct-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    prone-position    valid    not-ready    not-implemented    alternative    sct    datetime

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position    Generic Therapy - Prone Position sct datetime    create-prone-position-in-progress_sct-datetime.json
    procedure.validate response - 201



032 Create Generic Therapy - Prone Position sct period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-prone-position-in-progress_sct-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    prone-position    valid    not-ready    not-implemented    alternative    sct    period

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position    Generic Therapy - Prone Position sct period   create-prone-position-in-progress_sct-period
    procedure.validate response - 201



033 Create Generic Therapy - Prone Position sct period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-prone-position-in-progress_sct-period2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    prone-position    valid    not-ready    not-implemented    alternative    sct    period_2

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position    Generic Therapy - Prone Position sct period_2    create-prone-position-in-progress_sct-period2.json
    procedure.validate response - 201



