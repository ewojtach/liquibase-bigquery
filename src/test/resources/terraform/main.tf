locals {
  deployer_email = "$var.deployer_sa@$project_id.iam.gserviceaccount.com"
}

module "bigquery" {
  source            = "terraform-google-modules/bigquery/google" # Path to the module
  version           = "~> 2.0.0"
  dataset_id        = "harness_test_ds"
  dataset_name      = "harness_test_ds"
  description       = "DataSet for liquibase harness tests to be run" # updated the description accordingly
  project_id        = var.project_id
  location          = "US" # Update location if needed
  dataset_labels    = {}
  time_partitioning = "DAY"
}
