# [Gold III] 나무 위의 벌레 - 2132 

[문제 링크](https://www.acmicpc.net/problem/2132) 

### 성능 요약

메모리: 20500 KB, 시간: 180 ms

### 분류

너비 우선 탐색, 깊이 우선 탐색, 그래프 이론, 그래프 탐색, 트리

### 제출 일자

2025년 1월 19일 19:37:58

### 문제 설명

<p>전산학(Computer science)에서 트리란 사이클이 없는 그래프를 말한다. 트리(Tree)라는 이름이 의미하듯, 이러한 구조는 나무의 모습에서 유래한다. 즉, 트리의 각 간선(edge)들이 나무의 가지를 나타내고, 각 정점(node)들은 가지가 갈라지는 지점을 의미한다. 또한 트리의 루트는 나무의 뿌리를 의미한다. 이러한 구조는 일반적인 나무의 구조에 해당하지만, 트리 자체의 성질에 주목하면 실제 나무와는 다소 다른 구조가 되기도 한다.</p>

<p>우리가 생각하려는 나무는 루트가 없는 트리이다. 이때 트리의 각각의 간선은 나무의 가지에 해당하고, 트리의 각 정점은 나무 위에서 열매가 매달려있는 지점을 의미한다. 각각의 정점에는 몇 개의 열매가 매달려 있다. 물론 열매 없이 가지가 갈라지는 경우도 있으므로, 이러한 경우는 그 노드에 0개의 열매가 매달려 있다고 생각하기로 하자.</p>

<p>이러한 나무 위에 한 마리의 벌레가 있다. 이 벌레는 임의의 정점에서 이동하기 시작한다. 벌레가 한 정점에 있을 때에는, 그 정점에 있는 열매들을 먹을 수 있다. 열매들을 다 먹은 후에는 가지를 따라서 다른 정점으로 이동한다. 만약 이동할 수 있는 가지가 여러 개 있다면 그 중 하나를 임의로 선택하지만, 한 번 지났던 가지는 다시 지날 수 없다. 벌레의 이동은 더 이상 이동할 수 있는 정점이 없을 때에 끝난다.</p>

<p>나무의 모양이 주어졌을 때, 벌레가 최대로 먹을 수 있는 열매의 수와 이때 어느 정점에서 이동을 시작해야 하는지를 알아내는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에는 트리의 정점의 개수를 나타내는 정수 n(1 ≤ n ≤ 10,000)이 주어진다. 다음 줄에는 차례로 1번, 2번, …, n번 정점에 매달려 있는 열매의 개수가 주어진다. 다음 n-1개의 줄에는 트리의 각 간선을 나타내는 서로 다른 두 자연수 A, B(1 ≤ A, B ≤ n)가 주어진다. 이는 트리의 A번 정점과 B번 정점이 연결되어 있음을 의미한다. 나무에 매달려 있는 열매의 총 개수는 2<sup>31</sup>-1 (2,147,483,647)개를 넘지 않는다.</p>

### 출력 

 <p>첫째 줄에 벌레가 먹을 수 있는 열매의 최대 개수와, 이때 이동을 시작할 정점의 번호를 출력한다. 답이 여러 개 있을 경우에는 정점의 번호가 가장 작은 경우를 출력한다.</p>

