BRANCH ?= master
SUBSCRIPTION ?= "jessde"
RG ?= jdk8s-us

.PHONY: local-clean git-clean helm-delete azd-clean

all:
	-make local-clean
	-make git-clean
	-make helm-delete
	make azd-clean

local-clean:
	-kubectx docker-for-desktop
	-helm delete --purge spring-boot-websocket-chat-demo
	-rm -rf charts draft.toml Dockerfile .draftignore .dockerignore .draft-tasks.toml target .classpath .project

# delete-branch: 
# 	-git branch -d $(BRANCH)
# 	-git push origin -d $(BRANCH) && echo "$(branch) branch successfully deleted"
# 	-git fetch --prune

git-clean:
	-rm -rf charts
	-git rm -rf azure-pipelines.yml Dockerfile draft.toml .dockerignore .draft-tasks.toml .draftignore charts
	-git commit -m "reset demo"
	-git push

helm-delete:
	-kubectx jdk8s-us
	-helm delete --purge chat-dev
	-helm delete --purge chat-prod
	-kubectx docker-for-desktop

azd-clean:
	@scripts/azdo-cleanup.sh
