terraform {
  required_version = "~> 0.12.6"
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = ">= 4.0.0"
    }
    google-beta = {
      source  = "hashicorp/google-beta"
      version = ">= 4.0.0"
    }
    random = {
      source  = "hashicorp/random"
      version = ">= 3.3.2"
    }
    github = {
      source  = "integrations/github"
      version = "~> 4.0"
    }
  }
}
