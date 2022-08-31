provider "google" {
  impersonate_service_account = var.deployer_sa
}

provider "google-beta" {
  impersonate_service_account = var.deployer_sa
}
