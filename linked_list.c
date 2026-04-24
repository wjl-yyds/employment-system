#include <stdio.h>
#include <stdlib.h>

// 定义节点结构
typedef struct Node {
    int data;
    struct Node *next;
} Node;

// 创建新节点
Node* createNode(int value) {
    Node *newNode = (Node *)malloc(sizeof(Node));
    if (newNode == NULL) {
        printf("内存分配失败\n");
        return NULL;
    }
    newNode->data = value;
    newNode->next = NULL;
    return newNode;
}

// 在链表头部插入节点
Node* insertAtHead(Node *head, int value) {
    Node *newNode = createNode(value);
    if (newNode == NULL) return head;
    newNode->next = head;
    return newNode;
}

// 在链表尾部插入节点
Node* insertAtTail(Node *head, int value) {
    Node *newNode = createNode(value);
    if (newNode == NULL) return head;
    
    if (head == NULL) {
        return newNode;
    }
    
    Node *current = head;
    while (current->next != NULL) {
        current = current->next;
    }
    current->next = newNode;
    return head;
}

// 在指定位置后面插入节点
Node* insertAfter(Node *head, int targetValue, int newValue) {
    Node *current = head;
    Node *newNode = createNode(newValue);
    if (newNode == NULL) return head;
    
    while (current != NULL && current->data != targetValue) {
        current = current->next;
    }
    
    if (current == NULL) {
        printf("未找到值为 %d 的节点\n", targetValue);
        free(newNode);
        return head;
    }
    
    newNode->next = current->next;
    current->next = newNode;
    return head;
}

// 删除链表中的节点
Node* deleteNode(Node *head, int value) {
    if (head == NULL) {
        printf("链表为空\n");
        return NULL;
    }
    
    // 删除头节点
    if (head->data == value) {
        Node *temp = head;
        head = head->next;
        free(temp);
        return head;
    }
    
    // 删除其他节点
    Node *current = head;
    while (current->next != NULL && current->next->data != value) {
        current = current->next;
    }
    
    if (current->next == NULL) {
        printf("未找到值为 %d 的节点\n", value);
        return head;
    }
    
    Node *temp = current->next;
    current->next = temp->next;
    free(temp);
    return head;
}

// 查找节点
Node* searchNode(Node *head, int value) {
    Node *current = head;
    while (current != NULL) {
        if (current->data == value) {
            return current;
        }
        current = current->next;
    }
    return NULL;
}

// 遍历链表并打印
void printList(Node *head) {
    if (head == NULL) {
        printf("链表为空\n");
        return;
    }
    
    printf("链表内容: ");
    Node *current = head;
    while (current != NULL) {
        printf("%d -> ", current->data);
        current = current->next;
    }
    printf("NULL\n");
}

// 获取链表长度
int getLength(Node *head) {
    int length = 0;
    Node *current = head;
    while (current != NULL) {
        length++;
        current = current->next;
    }
    return length;
}

// 反转链表
Node* reverseList(Node *head) {
    Node *prev = NULL;
    Node *current = head;
    Node *next = NULL;
    
    while (current != NULL) {
        next = current->next;
        current->next = prev;
        prev = current;
        current = next;
    }
    return prev;
}

// 释放整个链表
void freeList(Node *head) {
    Node *current = head;
    while (current != NULL) {
        Node *temp = current;
        current = current->next;
        free(temp);
    }
}

// 主函数 - 演示链表的使用
int main() {
    Node *head = NULL;
    
    printf("=== C语言链表演示 ===\n\n");
    
    // 1. 在尾部插入节点
    printf("1. 在尾部依次插入 10, 20, 30, 40\n");
    head = insertAtTail(head, 10);
    head = insertAtTail(head, 20);
    head = insertAtTail(head, 30);
    head = insertAtTail(head, 40);
    printList(head);
    
    // 2. 在头部插入节点
    printf("\n2. 在头部插入 5\n");
    head = insertAtHead(head, 5);
    printList(head);
    
    // 3. 在指定位置后面插入
    printf("\n3. 在值为 20 的节点后面插入 25\n");
    head = insertAfter(head, 20, 25);
    printList(head);
    
    // 4. 获取链表长度
    printf("\n4. 链表长度: %d\n", getLength(head));
    
    // 5. 查找节点
    printf("\n5. 查找值为 30 的节点: ");
    if (searchNode(head, 30) != NULL) {
        printf("找到\n");
    } else {
        printf("未找到\n");
    }
    
    // 6. 删除节点
    printf("\n6. 删除值为 25 的节点\n");
    head = deleteNode(head, 25);
    printList(head);
    
    // 7. 反转链表
    printf("\n7. 反转链表\n");
    head = reverseList(head);
    printList(head);
    
    // 8. 释放链表
    printf("\n8. 释放链表内存\n");
    freeList(head);
    head = NULL;
    printf("链表已释放\n");
    
    return 0;
}
