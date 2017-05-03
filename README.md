# PaneRecyclerViewAdapter
RecyclerViewにヘッダーとフッターをつける

# 使い方
```java
// 使いたいアダプタを作る
MyAdapter adapter = new MyAdapter();

// PaneRecyclerViewAdapter作る
PaneRecyclerViewAdapter paneRecyclerViewAdapter = new PaneRecyclerViewAdapter(adapter); // アダプタあげる

// GridViewならLayoutManagerあげる
//paneRecyclerViewAdapter.setGridLayoutManager(gridLayoutManager);

// ヘッダーとフッターのビューをあげる
paneRecyclerViewAdapter.setHeaderView(headerView);
paneRecyclerViewAdapter.setFooterView(footerView);

// RecyclerViewにPaneRecyclerVieAdapterをあげる
recyclerView.setAdapter(paneRecyclerViewAdapter);
```
