locals {
  deployer_email = "$var.deployer_sa@$project_id.iam.gserviceaccount.com"
}

module "bigquery" {
  source            = "terraform-google-modules/bigquery/google" # Path to the module
  version           = "~> 2.0.0" # Specify the version of the module you require
  dataset_id        = "harness-test"
  dataset_name      = "harness-test"
  description       = "DataSet for liquibase harness tests to be run" # updated the description accordingly
  project_id        = var.project_id
  location          = "US" # Update location if needed
  dataset_labels    = {}
  time_partitioning = "DAY"
}
