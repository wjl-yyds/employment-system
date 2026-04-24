#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode {
    int data;
    struct TreeNode *left;
    struct TreeNode *right;
} TreeNode;

TreeNode *createNode(int value) {
    TreeNode *newNode = (TreeNode *)malloc(sizeof(TreeNode));
    if (newNode == NULL) {
        printf("内存分配失败\n");
        return NULL;
    }

    newNode->data = value;
    newNode->left = NULL;
    newNode->right = NULL;
    return newNode;
}

TreeNode *insertNode(TreeNode *root, int value) {
    if (root == NULL) {
        return createNode(value);
    }

    if (value < root->data) {
        root->left = insertNode(root->left, value);
    } else if (value > root->data) {
        root->right = insertNode(root->right, value);
    }

    return root;
}

TreeNode *searchNode(TreeNode *root, int value) {
    if (root == NULL || root->data == value) {
        return root;
    }

    if (value < root->data) {
        return searchNode(root->left, value);
    }

    return searchNode(root->right, value);
}

void inorderTraversal(TreeNode *root) {
    if (root == NULL) {
        return;
    }

    inorderTraversal(root->left);
    printf("%d ", root->data);
    inorderTraversal(root->right);
}

void preorderTraversal(TreeNode *root) {
    if (root == NULL) {
        return;
    }

    printf("%d ", root->data);
    preorderTraversal(root->left);
    preorderTraversal(root->right);
}

void postorderTraversal(TreeNode *root) {
    if (root == NULL) {
        return;
    }

    postorderTraversal(root->left);
    postorderTraversal(root->right);
    printf("%d ", root->data);
}

void freeTree(TreeNode *root) {
    if (root == NULL) {
        return;
    }

    freeTree(root->left);
    freeTree(root->right);
    free(root);
}

int main() {
    TreeNode *root = NULL;
    int values[] = {50, 30, 70, 20, 40, 60, 80};
    int size = sizeof(values) / sizeof(values[0]);
    int i;

    printf("=== C语言二叉搜索树示例 ===\n\n");

    for (i = 0; i < size; i++) {
        root = insertNode(root, values[i]);
    }

    printf("中序遍历: ");
    inorderTraversal(root);
    printf("\n");

    printf("前序遍历: ");
    preorderTraversal(root);
    printf("\n");

    printf("后序遍历: ");
    postorderTraversal(root);
    printf("\n");

    if (searchNode(root, 60) != NULL) {
        printf("查找结果: 找到节点 60\n");
    } else {
        printf("查找结果: 未找到节点 60\n");
    }

    freeTree(root);
    return 0;
}
